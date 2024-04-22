# System Testing

* T1: test_login_success
  * This will test to see if the user can successfully log in with the correct credentials or not
    * Step1: Go to the login page of the website
    * Step2: login with this credential (user that exists in the database)
      * email: 1@gmail.com
      * password: 123456
    * if we can the username of the user logged in, it means they logged in successfully.


* T2: test_login_fail
  * This will test for when user entered invalid credentials during login process
    * Step1: Go to login page
    * Step2: login with this credential (user doesn't exist in the database)
      * email: wrongemail@gmail.com
      * password: wrong_pwd
    * Since we won't find any username of the user logged in, it means the login failed


* T3: test_register_success
  * This will test to see if the user can register successfully with the right password requirements and unique email
    * Step1: Go to register page
    * Step2: Fill in the register form
      * As long as the following conditions are met, they should be able to register successfully:
        * Email that doesn't already exist in the database
        * Password that follows the listed requirement
        * All fields are entered


* T4: test_register_fail
  * This will test to see if the user cannot register due to some input failures
    * Step1: Go to register page
    * Step2: Fill in the register form
    * Registration can fail due to the following reasons:
      * Email already existed in the database
      * Password requirements not met
      * Some fields are empty


* T5: test_logout
  * This will test to see if the user can logout when they are on the homepage (after they logged in)
    * Step1: The user must be logged in
    * Step2: Click on the logout button on the homepage
    * Step3: After clicking on the logout button, it should redirect them back to the login page
      * On the login page, there should be a text saying "Login to start browsing!"
        * If the selenium web driver can find that text, it means they user logged out successfully


* T6: test_upload_product
  * This will test to see if the user can upload the product successfully
    * Step1: The user must be logged in
    * Step2: Click on the upload button on the homepage. An upload form will appear.
    * Step3: Fill in the form and make sure all the fields are filled
    * Step4: Click on submit button.
      * If there's an error or something is missing, user will get the message.
      * If everything is filled out correctly and no issues occur, the upload form will disappear and it will redirect the user
        back to the homepage


* T7: test_user_listing
  * This will test to see if the user can go to their product listing page (where they can see the products they have uploaded)
    * Step1: The user must be logged in
    * Step2: Click on the username on the navigation bar
    * After that, it should redirect the user to a page with the heading "MY LISTINGS"


* T8: test_view_contact
  * This will test to see if the user can view the seller's contact info for each product on the marketplace
  * Step1: The user must be logged in
  * Step2: Click on the "View contact info" button under the desired product
  * After that, the seller's contact info should appear under the selected product