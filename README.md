# ClientBrasilPrev
This project is developed for a BrasilPrev's test and is hosted on Heroku: https://client-brasilprev.herokuapp.com

Swagger: https://client-brasilprev.herokuapp.com/swagger-ui.html#/

To generate the token, send a post to the route: 

  Post: https://client-brasilprev.herokuapp.com/oauth/token 
  
  Basic Auth: 
  
    Username: user
    
    Password: secret
    
  Headers:
  
    ContentType: application/x-www-form-urlencoded
    
  Body as x-www-form-urlencoded:
  
  To Generate a token
  
    grant_type:password
    
    username:user
    
    password:secret
    
  To refresh a token
  
    grant_type:refresh_token
    
    refresh_token: "GeneratedRefresh_Token"
    
    
  Obs.: 
  All these users(clientId and user) are saved in the database, but their password are encrypted.
  The access_token will expire in 360 seconds and the refresh token will expire in 900000 seconds.
  
  Expected answer:
    ![image](https://user-images.githubusercontent.com/18118193/91001341-d4bef080-e5a1-11ea-8b8a-80560c0cddc6.png)


These are the controllers created for the Client and another(*/users/me) just to see some particularities about the token used.

Just a warning: The next step for this application should be to validate the cpf and make the address an object. Due to lack of time, I was unable to make these adjustments.

![clientcontroller](https://user-images.githubusercontent.com/18118193/91000870-8b21d600-e5a0-11ea-9147-4949eb6edb9e.PNG)

You will need Authorize your acces, so generate the token and click on the Authorize with a lock. After you selected, input "Bearer " and your acces_token generated:

![token authorize](https://user-images.githubusercontent.com/18118193/91002055-eef9ce00-e5a3-11ea-8126-4b62060824a9.PNG)

Now you are able to test the api.
