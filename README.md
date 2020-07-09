# Notes Service

A note service with JWT authentication and MySQL data base

## API Endpoints

Below are described the REST endpoints available that you can use to handle notes

### User endponits

#### Create User

First of all you will need create a new user

`http://localhost:4000/signup`

#### Log In user

`http://localhost:4000/login`

With the next body:

``` JSON
{
  "username": "mike",
  "password": "password"
}
```

This will provide you with:

1. A JWT that you will have to **save and send in each request**
2. Username of the logged user

### Notes endpoints

- :warning:  Send the JWT in each request through the header **"Authirization"**

#### Get all notes

`http://localhost:4000/api/notes/`

- Send username through the header **"username"**

#### Post new note

`http://localhost:4000/api/notes/`

With the next body:

``` JSON
{
  "title": "title",
  "content": "note content",
  "user": {
    "username": "username"
   }
}
```

#### Delete note

`http://localhost:4000/api/notes/(noteId)`

#### Update note

`http://localhost:4000/api/notes/(noteId)`

With the next body:

```JSON
{
  "title": "xd",
  "content": "date now",
  "user": {
    "username": "mike"
  }
}
```

#### Search notes

`http://localhost:4000/api/notes/search?query=(query)`

- Send username through the header **"username"**
