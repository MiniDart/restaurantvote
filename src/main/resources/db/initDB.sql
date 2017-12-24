DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS menu_items;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS menu_items_history;
DROP TABLE IF EXISTS votes_history;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR(255)            NOT NULL,
  password   VARCHAR(255)            NOT NULL,
  registered TIMESTAMP DEFAULT now() NOT NULL,
  enabled    BOOLEAN DEFAULT TRUE    NOT NULL
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE restaurants
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR(255) NOT NULL
);

CREATE TABLE menu_items
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  restaurant_id INTEGER      NOT NULL,
  name          VARCHAR(255) NOT NULL,
  price         INTEGER      NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
);

CREATE UNIQUE INDEX menu_restaurant_id_name_idx ON menu_items (restaurant_id, name);

CREATE TABLE votes
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id       INTEGER   NOT NULL,
  restaurant_id INTEGER   NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE UNIQUE INDEX votes_user_id_idx ON votes (user_id);

CREATE TABLE votes_history
(
  id            INTEGER PRIMARY KEY,
  user_id       INTEGER   NOT NULL,
  restaurant_id INTEGER   NOT NULL,
  date    DATE NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX votes_history_user_id_date_idx ON votes_history (user_id, date);

CREATE TABLE menu_items_history
(
  id            INTEGER PRIMARY KEY,
  restaurant_id INTEGER      NOT NULL,
  name          VARCHAR(255) NOT NULL,
  price         INTEGER      NOT NULL,
  date          DATE    NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE UNIQUE INDEX menu_history_restaurant_id_name_date_idx ON menu_items_history (restaurant_id, name, date);