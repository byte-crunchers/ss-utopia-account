# ss-utopia-account

### Accounts microservice
- Backend microservice that handles accounts & cards.
- Routes are secured with JWT authorization & HTTPS.
- Routes are accessed through Spring cloud gateway & Eureka server.

#### REST controller methods:	
- Create a new account type
- Get all account types
- Get all accounts summary
- Delete an account
- suspend an account
- view credit limit by credit number
- increase credit limit by credit number  
- Apply for a card
- Get cards by user ID

#### Database relationships:
- [User] -> [Account] -> [Card]
- A user can have multiple accounts.
- An account can have 1 or 0 cards.
- A credit account has a credit card.
- A checking account has a debit card.
- A savings account has no card.

#### Account types:
- Credit Cards: "Basic Credit", "Platinum Credit", "Plus Credit", "Foodies Credit"
- Checking Account: "Utopia Debit"
- Savings Account: "Utopia Savings"
