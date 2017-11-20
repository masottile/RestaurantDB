## MP5: Restaurants, Queries, and Statistical Learning Design Plan

Maria Sottile and Ge (Jessica) Ma

What we know about the JSON files

```
RESTAURANTS
open: boolean (???)
url: string url to yelp profile
longitude: number 
neighborhood: array of strings ("Telegraph Ave", "UC Campus Area", "Downtown Berkeley")
business_id: string of letters, underscores, numbers (yelp-specific thing)
name: string 
categories: array of strings ("Cafe", "Restaurants", "Chinese")
state: string 
type: string ("business")
stars: number (1-5)
city: string
full_address: string 
review count: number 
photo_url: string (link to photo)
schools: array of strings (but probably will only have one, i.e. UCB)
latitude: number 
price: number (1-3)

REVIEWS
type: string ("review")
business_id: string of letters
votes: child object with fields cool, useful, funny which each have a number (number of people who reacted to each)
review_id: string of letters, numbers, underscores
text: string of the entire review
stars: number of stars given (1-5) 
user_id: same format as business id it seems
date: string (YYYY-MM-DD)

USERS
url: string (to profile of a user)
votes: same as in reviews 
review_count: number
type: string ("user")
user_id: string
name: string
average stars: number (average stars given)
```

