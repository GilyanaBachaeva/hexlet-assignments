<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Создание статьи</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Создание новой статьи</h1>
        <form action="/articles" method="post">
            <div class="form-group">
                <label for="title">Название статьи</label>
                <input type="text" class="form-control" id="title" name="title" value="{{article.title}}" required>
            </div>
            <div class="form-group">
                <label for="content">Содержимое статьи</label>
                <textarea class="form-control" id="content" name="content" rows="5" required>{{article.content}}</textarea>
            </div>
            <button type="submit" class="btn btn-primary">Создать статью</button>
        </form>
        {{#if error}}
            <div class="alert alert-danger mt-3">{{error}}</div>
        {{/if}}
    </div>
</body>
</html>
