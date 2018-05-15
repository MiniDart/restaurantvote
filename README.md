It's a REST API for voting system for deciding where to have a lunch.

Description:
 1. 2 types of users: admin and regular users.
 2. Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price).
 3. Menu changes each day (admins do the updates).
 4. Users can vote on which restaurant they want to have lunch at.
 5. Only one vote counted per user.
 6. If user votes again the same day:

        -If it is before 11:00 we asume that he changed his mind.
        -If it is after 11:00 then it is too late, vote can't be changed.


Technologies that were used: Spring MVC, Spring Security, Hibernate, HSQLDB.

HTTP basic authentication is used.
Login and password for testing:
* for regular user - login: user1@mail.com, password: _password1_.
* for admin - login: admin@mail.com, password: _admin_.


API.

For regular users:

* GET /profile. Get authorized user.
* POST /profile (new user in request body). Create user.
* DELETE /profile. Delete authorized user.
* PUT /profile (updated user in request body). Update user.
* GET /profile/vote. Get vote of authorized user.
* POST /profile/vote (vote in request body). Create user's vote.
* PUT /profile/vote (vote in request body). Update user's vote.
* GET /profile/history (parameters: start - beginning of search date, end - end of search date. Both can be empty). Get history of user's votes.
* GET /restaurants/{id} (path variables: id - restaurant's id). Get restaurant.
* GET /restaurants/menu. Get all restaurants with menu.
* GET /restaurants/votes. Get all restaurants with quantity of their votes.
* GET /restaurants/{id}/votes (path variables: id - restaurant's id). Get restaurant with specific id with quantity of its votes.
* GET /restaurants/history (parameters: start - beginning of search date, end - end of search date. Both can be empty). Get all restaurants with history of their menu.
* GET /restaurants/{id}/history (path variables: id - restaurant's id; parameters: start - beginning of search date, end - end of search date. Both can be empty). Get restaurant with specific id with history of its menu.
* GET /restaurants/{restaurantId}/menu (path variables: restaurantId - restaurant's id). Get menu of specific restaurant.
* GET /restaurants/{restaurantId}/menu/{id} (path variables: restaurantId - restaurant's id, id - id of menu). Get menu item with specific id from specific restaurant.

For admins:

* GET /admin/users. Get all users.
* GET /admin/users/{id} (path variables: id - user's id). Get specific user.
* POST /admin/users (new user in request body). Create user.
* DELETE /admin/users/{id} (path variables: id - user's id). Delete specific user.
* PUT /admin/users/{id} (path variables: id - user's id; updated user in request body). Update specific user.
* GET /admin/users/by (parameters: email - user's email). Get user by email.
* POST /admin/clear. Move all votes to history and copy all menu to history.
* GET /admin/votes. Get all votes.
* GET /admin/users/history (parameters: start - beginning of search date, end - end of search date. Both can be empty). Get vote's history of all users.
* GET /admin/users/{id}/history (path variables: id - user's id; parameters: start - beginning of search date, end - end of search date. Both can be empty). Get vote's history of specific user.
* POST /restaurants (new restaurant in request body). Create restaurant.
* DELETE /restaurants/{id} (path variables: id - restaurants's id). Delete specific restaurant.
* PUT /restaurants/{id} (path variables: id - restaurants's id; updated restaurant in request body). Update specific restaurant.
* POST /restaurants/{restaurantId}/menu (path variables: restaurantId - restaurant's id; new item of menu in request body). Create item of menu in specific restaurant.
* DELETE /restaurants/{restaurantId}/menu/{id} (path variables: restaurantId - restaurant's id, id - id of menu). Delete specific item of menu in specific restaurant.
* DELETE /restaurants/{restaurantId}/menu (path variables: restaurantId - restaurant's id). Delete all menu of specific restaurant.
* PUT /restaurants/{restaurantId}/menu/{id} (path variables: restaurantId - restaurant's id, id - id of menu; updated item of menu in request body). Update specific item of menu in specific restaurant.