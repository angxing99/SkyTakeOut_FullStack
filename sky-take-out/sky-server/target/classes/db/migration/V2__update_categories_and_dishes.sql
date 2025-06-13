-- Add image column to category if it doesn't exist
ALTER TABLE `category` ADD COLUMN `image` varchar(255) DEFAULT NULL COMMENT '分类图片';

-- Update category names and images
UPDATE `category` SET
                      name = 'Chinese Cuisine',
                      image = 'https://t4.ftcdn.net/jpg/01/43/31/51/360_F_143315166_eRGH3svXQ2j8WRjcTMTzAKZ61nKf6dUa.jpg'
WHERE id = 11;

UPDATE `category` SET
                      name = 'Japanese Cuisine',
                      image = 'https://static.vecteezy.com/system/resources/previews/013/277/493/non_2x/sushi-logo-template-design-seafood-or-traditional-japanese-cuisine-with-salmon-delicious-food-logo-for-japanese-restaurant-bar-sushi-shop-free-vector.jpg'
WHERE id = 12;

UPDATE `category` SET
                      name = 'Western Set Meals',
                      image = 'https://thumbs.dreamstime.com/b/spoon-fork-clockwise-clock-time-lunch-cafe-eatery-restaurant-food-logo-design-vector-242716107.jpg'
WHERE id = 13;

UPDATE `category` SET
                      name = 'Indian Cuisine',
                      image = 'https://thumbs.dreamstime.com/b/indian-food-logo-round-linear-turban-spoon-yellow-background-eps-166330970.jpg'
WHERE id = 15;

UPDATE `category` SET
                      name = 'Thai Cuisine',
                      image = 'https://static.vecteezy.com/system/resources/previews/007/884/940/non_2x/thai-rooftop-restaurant-logo-design-vector.jpg'
WHERE id = 16;

UPDATE `category` SET
                      name = 'Korean Cuisine',
                      image = 'https://img0-placeit-net.s3-accelerate.amazonaws.com/uploads/stage/stage_image/36190/optimized_product_thumb_stage.jpg'
WHERE id = 17;

UPDATE `category` SET
                      name = 'Italian Cuisine',
                      image = 'https://www.shutterstock.com/image-vector/pizzeria-vintage-vector-logo-white-260nw-2461273959.jpg'
WHERE id = 18;

UPDATE `category` SET
                      name = 'Mexican Cuisine',
                      image = 'https://t3.ftcdn.net/jpg/06/10/50/30/360_F_610503055_uLiiUz8xzSyLNxlVgo58XKskZUtTSNHm.jpg'
WHERE id = 19;

UPDATE `category` SET
                      name = 'Middle Eastern Cuisine',
                      image = 'https://www.shutterstock.com/image-vector/arabic-cuisine-logo-design-authentic-600nw-1118251439.jpg'
WHERE id = 20;

-- Clear existing dishes
DELETE FROM `dish_flavor`;
DELETE FROM `dish`;

-- Insert new dish rows
INSERT INTO `dish` VALUES
                       (46,'Kung Pao Chicken',11,6.00,'https://theyummybowl.com/wp-content/uploads/kung-pao-chicken-n-1-of-1.jpg','Spicy stir-fried chicken with peanuts and vegetables',1,'2022-06-09 22:40:47','2022-06-09 22:40:47',1,1),
                       (47,'Sweet and Sour Pork',11,4.00,'https://cdn.sanity.io/images/2r0kdewr/production/0bc6f529c1200c84a8465d9317c3029898a4d4fa-1500x844.jpg','Deep-fried pork in a tangy pineapple sauce',1,'2022-06-10 09:18:49','2022-06-10 09:18:49',1,1),
                       (48,'Mapo Tofu',11,4.00,'https://omnivorescookbook.com/wp-content/uploads/2022/05/220510_Mapo-Tofu_550.jpg','Silken tofu in spicy Sichuan chili and bean sauce',1,'2022-06-10 09:22:54','2022-06-10 09:22:54',1,1),

                       (49,'Sushi Platter',12,2.00,'https://images.squarespace-cdn.com/...','Assorted nigiri and maki rolls',1,'2022-06-10 09:30:17','2022-06-10 09:30:17',1,1),
                       (50,'Chicken Katsu Curry',12,1.00,'https://japan.recipetineats.com/...','Breaded chicken with Japanese curry sauce and rice',1,'2022-06-10 09:34:28','2022-06-10 09:34:28',1,1),
                       (51,'Ramen',12,56.00,'https://www.kikkoman.eu/...','Japanese wheat noodles in pork or miso broth',1,'2022-06-10 09:40:51','2022-06-10 09:40:51',1,1),

                       (52,'Fried Chicken Meal',13,66.00,'https://static01.nyt.com/...','Crispy chicken with coleslaw and fries',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (53,'Grilled Ribeye Steak Set',13,66.00,'https://bluejeanchef.com/...','Pasta with rich tomato-meat sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (54,'Butter Chicken with Naan',15,66.00,'https://www.missionfoods.com/...','Creamy tomato chicken curry with flatbread',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (55,'Palak Paneer',15,66.00,'https://www.indianhealthyrecipes.com/...','Spinach curry with Indian cheese',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (56,'Biryani Rice',15,66.00,'https://www.indianhealthyrecipes.com/...','Fragrant basmati rice with spices and marinated chicken',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (57,'Pad Thai',16,66.00,'https://cookingwithgenius.com/...','Stir-fried rice noodles with shrimp, tofu, peanuts, and bean sprouts',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (58,'Green Curry Chicken',16,66.00,'https://hot-thai-kitchen.com/...','Thai green curry with tender chicken, eggplant, and coconut milk',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (59,'Tom Yum Soup',16,66.00,'https://hot-thai-kitchen.com/...','Hot and sour Thai soup with shrimp, lemongrass, and lime',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (60,'Bibimbap',17,66.00,'https://upload.wikimedia.org/...','Mixed rice bowl with assorted vegetables, meat, and gochujang sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (61,'Kimchi Stew (Kimchi Jjigae)',17,66.00,'https://www.maangchi.com/...','Spicy Korean stew made with aged kimchi, tofu, and pork',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (62,'Korean Fried Chicken',17,66.00,'https://www.kitchensanctuary.com/...','Crispy double-fried chicken with sweet and spicy sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (63,'Margherita Pizza',18,66.00,'https://allforpizza.com/...','Classic pizza with tomato, mozzarella, and basil',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (64,'Fettuccine Alfredo',18,66.00,'https://www.allrecipes.com/...','Creamy pasta with parmesan cheese and butter sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (65,'Lasagna',18,66.00,'https://www.allrecipes.com/...','Layered pasta with rich meat sauce, cheese, and béchamel',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (66,'Beef Tacos',19,66.00,'https://loveandgoodstuff.com/...','Corn tortillas filled with seasoned beef, lettuce, and cheese',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (67,'Chicken Quesadilla',19,66.00,'https://www.julieseatsandtreats.com/...','Grilled tortilla with melted cheese and grilled chicken',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (68,'Guacamole & Chips',19,66.00,'https://img.taste.com.au/...','Fresh avocado dip served with crispy tortilla chips',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (69,'Chicken Shawarma Plate',20,66.00,'https://colleenchristensennutrition.com/...','Marinated grilled chicken served with rice, salad, and garlic sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (70,'Falafel Wrap',20,66.00,'https://cookingwithayeh.com/...','Deep-fried chickpea balls wrapped in pita with tahini and veggies',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (71,'Lamb Kofta',20,66.00,'https://img.taste.com.au/...','Spiced ground lamb skewers grilled and served with yogurt sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1);
