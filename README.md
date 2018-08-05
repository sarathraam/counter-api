# counter-api

A RESTful API to handle the given task described below. The temporary credentials to use the API is user:password. The app is also hosted on heroku and can be accessed using the URL below.
PS: Heroku URL might work slow for the first time as the app would have been put to sleep during idling.

Access at: http://guarded-depths-54790.herokuapp.com/counter-api/

**Task description:**

Given a paragraph of text, implement 2 endpoints: 1. To find no. of occurences of given search texts. 2. Return top N frequent words in the paragraph.

> **1st endpoint:**
>
> Sample Request
> curl http://host/counter-api/search -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" -d’{“searchText”:[“Duis”, “Sed”, “Donec”, “Augue”, “Pellentesque”, “123”]}’ -H"Content-Type: application/json" –X POST
>
> Result in JSON:
> {"counts": [{"Duis": 11}, {"Sed": 16}, {"Donec": 8}, {"Augue": 7}, {"Pellentesque": 6}, {"123": 0}]}
> _________________________________________________________________________________________________________
>
> **2nd endpoint:**
>
> Sample Request
> curl http://host/counter-api/top/20 -H"Authorization: Basic b3B0dXM6Y2FuZGlkYXRlcw==" H”Accept: text/csv”
>
> Example: /top/5 should return:
> text1|100, text2|91, text3|80, text4|70, text5|60
