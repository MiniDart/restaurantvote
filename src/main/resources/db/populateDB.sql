DELETE FROM user_roles;
DELETE FROM menu_items;
DELETE FROM votes;
DELETE FROM menu_items_history;
DELETE FROM votes_history;
DELETE FROM users;
DELETE FROM restaurants;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, enabled, password) VALUES
  ('User1','user1@mail.com', TRUE , 'password1'),
  ('User2','user2@mail.com',TRUE ,'password2'),
  ('Admin','admin@mail.com', TRUE , 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_ADMIN', 100002),
  ('ROLE_USER', 100002);

INSERT INTO restaurants (name) VALUES
  ('Hagen'),
  ('Domino'),
  ('Chaplin');

INSERT INTO menu_items (restaurant_id,name,price) VALUES
  (100003,'Potato',12050),
  (100003,'Soup',20000),
  (100003,'Fry chicken',35000),
  (100004,'Hamburger',10070),
  (100004,'Potato',13000),
  (100004,'Boiled eggs',15000),
  (100005,'Beef stew',40000),
  (100005,'Baked turkey',8000),
  (100005,'Scrambled eggs',12000);

INSERT INTO votes (user_id, restaurant_id) VALUES
  (100000,100005),
  (100001,100004);


INSERT INTO menu_items_history (menu_item_id,restaurant_id,name, price, date) VALUES
  (100006,100003,'Potato',12050,'2016-03-07'),
  (100007,100003,'Soup',20000,'2016-03-08'),
  (100008,100003,'Fry chicken',35000,'2016-03-09'),
  (100009,100004,'Hamburger',10070,'2016-03-07'),
  (100010,100004,'Potato',13000,'2016-03-08'),
  (100011,100004,'Boiled eggs',15000,'2016-03-09'),
  (100012,100005,'Beef stew',40000,'2016-03-07'),
  (100013,100005,'Baked turkey',8000,'2016-03-08'),
  (100014,100005,'Scrambled eggs',12000,'2016-03-09');

