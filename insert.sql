SET foreign_key_checks=1;

USE teamdb;

INSERT INTO mst_user
(user_name, password, family_name, first_name, family_name_kana, first_name_kana, gender)
VALUES ('taro@gmail.com', '111111', '山田', '太郎', 'やまだ', 'たろう', 0);

INSERT INTO mst_category (category_name,category_description) VALUES
('スポーツ', 'スポーツチケットのカテゴリーです'),
('ライブ', 'ライブチケットのカテゴリーです'),
('映画', '映画チケットのカテゴリーです');

INSERT INTO mst_product(product_name,product_name_kana,product_description,category_id,price,image_full_path,release_date,release_company) VALUES 
('日の丸JAPAN','ひのまるじゃぱん','W杯直前の大事な一戦',1,6000,'/img/soccer.jpg','2025/12/31','埼玉スーパースタジアム'),
('ドジャース','どじゃーす','ドジャースが日本で試合する貴重な一戦',1,10000,'/img/baseball.jpg','2025/10/08','東京ドーム'),
('嵐LastTour','あらしらすとつあー','解散前ラストライブ',2,9900,'/img/arashilive.jpg','2026/05/25','東京国立競技場'),
('米津玄師2025Tour','よねずげんし2025つあー','日本を代表するアーティストの2025ライブ',2,9900,'/img/yonezulive.jpg','2025/09/15','さいたまスーパーアリーナ'),
('STARWARS','すたーうぉーず','第一作目から最新作まで一気見',3,2100,'/img/img_starwars.jpg','2025/11/17','グランドシネマサンシャイン池袋'),
('千と千尋の神隠し','せんとちひろのかみかくし','大人気作品が期間限定で公開',3,2100,'/img/img_kami.jpg','2025/08/10','全国の映画館');