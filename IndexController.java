package jp.co.internous.team2505.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.team2505.model.domain.MstCategory;
import jp.co.internous.team2505.model.domain.MstProduct;
import jp.co.internous.team2505.model.form.SearchForm;
import jp.co.internous.team2505.model.mapper.MstCategoryMapper;
import jp.co.internous.team2505.model.mapper.MstProductMapper;
import jp.co.internous.team2505.model.session.LoginSession;

/**
 * 商品検索に関する処理を行うコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/team2505")
public class IndexController {
	/*
	 * フィールド定義
	 */
	@Autowired
	private MstCategoryMapper categoryMapper;
	
	@Autowired
	private MstProductMapper productMapper;
	
	@Autowired
    private LoginSession session;
	
	/**
	 * トップページを初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/")
	public String index(Model m) { 
		if (!session.isLoggedIn() && session.getTmpUserId() == 0) {
			int tmpUserId = -1 * (new Random().nextInt(900_000_000) + 100_000_000);
	        session.setTmpUserId(tmpUserId);
	    }

		List<MstCategory> categoryList = categoryMapper.find();
		List<MstProduct> productList = productMapper.find();

		m.addAttribute("categories", categoryList);
		m.addAttribute("products", productList);
		m.addAttribute("loginSession", session);
		
		return "index";
	}

	/**
	 * 検索処理を行う
	 * @param f 検索用フォーム
	 * @param m 画面表示用オブジェクト
	 * @return トップページ
	 */
	@RequestMapping("/searchItem")
	public String searchItem(SearchForm f, Model m) {
		String keyword = f.getKeywords();
		if (keyword != null) {
		    keyword = keyword.replaceAll("　", " ");       
		    keyword = keyword.replaceAll("\\s{2,}", " ");  
		    keyword = keyword.trim();                      
		    f.setKeywords(keyword);
		}

		List<String> keywordList = new ArrayList<>();
		if (keyword != null && !keyword.isEmpty()) {
		    keywordList = Arrays.asList(keyword.split(" "));
		}
		String[] arrayKeywords = keywordList.toArray(new String[0]);

		List<MstProduct> productList;
		if (keywordList.isEmpty() && (f.getCategory() == null || f.getCategory() == 0)) {
		    productList = productMapper.find(); 
		} else if ((f.getCategory() == null || f.getCategory() == 0)){
			productList = productMapper.findByProductName(arrayKeywords);
		} else {
		    productList = productMapper.findByCategoryAndProductName(f.getCategory(), arrayKeywords);
		}

		List<MstCategory> categoryList = categoryMapper.find();

		m.addAttribute("keywords", f.getKeywords());
		m.addAttribute("selected", f.getCategory());
		m.addAttribute("products", productList);
		m.addAttribute("categories", categoryList);
		m.addAttribute("loginSession", session);

		return "index";	
	}
}
