-- Clear existing dishes
DELETE FROM `dish_flavor`;
DELETE FROM `dish`;

-- Insert new dish rows
INSERT INTO `dish` VALUES
                       (46,'Kung Pao Chicken',11,6.00,'https://theyummybowl.com/wp-content/uploads/kung-pao-chicken-n-1-of-1.jpg','Spicy stir-fried chicken with peanuts and vegetables',1,'2022-06-09 22:40:47','2022-06-09 22:40:47',1,1),
                       (47,'Sweet and Sour Pork',11,4.00,'https://cdn.sanity.io/images/2r0kdewr/production/0bc6f529c1200c84a8465d9317c3029898a4d4fa-1500x844.jpg','Deep-fried pork in a tangy pineapple sauce',1,'2022-06-10 09:18:49','2022-06-10 09:18:49',1,1),
                       (48,'Mapo Tofu',11,4.00,'https://omnivorescookbook.com/wp-content/uploads/2022/05/220510_Mapo-Tofu_550.jpg','Silken tofu in spicy Sichuan chili and bean sauce',1,'2022-06-10 09:22:54','2022-06-10 09:22:54',1,1),

                       (49,'Sushi Platter',12,2.00,'https://images.squarespace-cdn.com/content/v1/5021287084ae954efd31e9f4/1607440088370-DJJ2AOFVEZ106V9F4OH6/Sushi+Nigiri+Platter+%28flatlay+-+sq%29.jpg','Assorted nigiri and maki rolls',1,'2022-06-10 09:30:17','2022-06-10 09:30:17',1,1),
                       (50,'Chicken Katsu Curry',12,1.00,'https://japan.recipetineats.com/wp-content/uploads/2021/12/Katsu_Curry_7011bsq.jpg','Breaded chicken with Japanese curry sauce and rice',1,'2022-06-10 09:34:28','2022-06-10 09:34:28',1,1),
                       (51,'Ramen',12,56.00,'https://www.kikkoman.eu/fileadmin/_processed_/f/0/csm_1103-recipe-page-Spicy-Kimchi-Ramen-with-Pork_desktop_c8dc4e51e8.jpg','Japanese wheat noodles in pork or miso broth',1,'2022-06-10 09:40:51','2022-06-10 09:40:51',1,1),

                       (52,'Fried Chicken Meal',13,66.00,'https://static01.nyt.com/images/2014/03/13/dining/chicken-still-2/chicken-still-2-articleLarge-v5.jpg','Crispy chicken with coleslaw and fries',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (53,'Grilled Ribeye Steak Set',13,66.00,'https://bluejeanchef.com/uploads/2020/05/Smoke-Spiced-Grilled-Ribeye-1280-665-640x800.jpg','Pasta with rich tomato-meat sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (54,'Butter Chicken with Naan',15,66.00,'https://www.missionfoods.com/wp-content/uploads/2022/06/easy-butter-chicken-naan.jpg','Creamy tomato chicken curry with flatbread',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (55,'Palak Paneer',15,66.00,'https://www.indianhealthyrecipes.com/wp-content/uploads/2020/06/palak-paneer-recipe.jpg','Spinach curry with Indian cheese',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (56,'Biryani Rice',15,66.00,'https://www.indianhealthyrecipes.com/wp-content/uploads/2022/02/biryani-rice-kuska-recipe.jpg','Fragrant basmati rice with spices and marinated chicken',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (57,'Pad Thai',16,66.00,'https://cookingwithgenius.com/wp-content/uploads/2025/01/0Z8A5545-2.jpg','Stir-fried rice noodles with shrimp, tofu, peanuts, and bean sprouts',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (58,'Green Curry Chicken',16,66.00,'https://hot-thai-kitchen.com/wp-content/uploads/2022/04/green-curry-new-sq-3.jpg','Thai green curry with tender chicken, eggplant, and coconut milk',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (59,'Tom Yum Soup',16,66.00,'https://hot-thai-kitchen.com/wp-content/uploads/2013/03/tom-yum-goong-blog.jpg','Hot and sour Thai soup with shrimp, lemongrass, and lime',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (60,'Bibimbap',17,66.00,'https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Dolsot-bibimbap.jpg/1200px-Dolsot-bibimbap.jpg','Mixed rice bowl with assorted vegetables, meat, and gochujang sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (61,'Kimchi Stew (Kimchi Jjigae)',17,66.00,'https://www.maangchi.com/wp-content/uploads/2007/11/kimchijjigae.jpg','Spicy Korean stew made with aged kimchi, tofu, and pork',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (62,'Korean Fried Chicken',17,66.00,'https://www.kitchensanctuary.com/wp-content/uploads/2019/08/Korean-Fried-Chicken-square-FS-New-7377.jpg','Crispy double-fried chicken with sweet and spicy sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (63,'Margherita Pizza',18,66.00,'https://allforpizza.com/wp-content/uploads/2022/07/1460A7EC-CF3B-40E8-B05F-A21E12E85EC6.jpeg','Classic pizza with tomato, mozzarella, and basil',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (64,'Fettuccine Alfredo',18,66.00,'https://www.allrecipes.com/thmb/ey5aal-JHk7PoNP92is_VCambmA=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/AR-23431-to-die-for-fettuccine-alfredo-DDMFS-beauty-3x4-b64d36c7ff314cb39774e261c5b18352.jpg','Creamy pasta with parmesan cheese and butter sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (65,'Lasagna',18,66.00,'https://www.allrecipes.com/thmb/k-7ylxn1DrCHEPif3lJrRBOhqkA=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/AR-19344-Homemade-Lasagna-beauty-2x1-98da45c3a5854a2b8901a92123ccb6f1.jpg','Layered pasta with rich meat sauce, cheese, and béchamel',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (66,'Beef Tacos',19,66.00,'https://loveandgoodstuff.com/wp-content/uploads/2020/08/classic-ground-beef-tacos-1200x1200.jpg','Corn tortillas filled with seasoned beef, lettuce, and cheese',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (67,'Chicken Quesadilla',19,66.00,'https://www.julieseatsandtreats.com/wp-content/uploads/2024/10/Chicken-Quesadilla-Square.jpg','Grilled tortilla with melted cheese and grilled chicken',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (68,'Guacamole & Chips',19,66.00,'https://img.taste.com.au/Y-LMFmPz/taste/2016/11/guacamole-with-crispy-tortilla-chips-23734-1.jpeg','Fresh avocado dip served with crispy tortilla chips',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),

                       (69,'Chicken Shawarma Plate',20,66.00,'https://colleenchristensennutrition.com/wp-content/uploads/2021/06/Up-close-chicken-shawarma-plate-with-a-striped-napkin.jpg','Marinated grilled chicken served with rice, salad, and garlic sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (70,'Falafel Wrap',20,66.00,'https://cookingwithayeh.com/wp-content/uploads/2024/03/Falafel-Wrap-SQ-1.jpg','Deep-fried chickpea balls wrapped in pita with tahini and veggies',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
                       (71,'Lamb Kofta',20,66.00,'https://img.taste.com.au/xq3Y_2e1/taste/2011/03/lamb-kofta-cropped-hero-198300-1.jpg','Spiced ground lamb skewers grilled and served with yogurt sauce',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1);



ALTER TABLE `dish` AUTO_INCREMENT = 72;
