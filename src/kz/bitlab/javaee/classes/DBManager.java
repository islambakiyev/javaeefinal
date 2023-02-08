package kz.bitlab.javaee.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_portal","root","");
        }catch (Exception e){

        }
    }

    public static ArrayList<Category> getAllCategories(){
        ArrayList<Category> categories = new ArrayList<>();

        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news_categories");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Long id = resultSet.getLong("cat_id");
                String name = resultSet.getString("cat_name");
                categories.add(new Category(id,name));
            }

            statement.close();
        }catch (Exception e){

        }
        return categories;
    }

    public static ArrayList<News> getAllNews(){
        ArrayList<News> news = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT news.id, news.post_date, news.title, news.content, " +
                            "news.description, cat.cat_id, cat.cat_name " +
                            "FROM content news " +
                            "INNER JOIN news_categories cat ON news.category_id = cat.cat_id "
            );
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getLong("cat_id"));
                category.setName(resultSet.getString("cat_name"));

                News article = new News();
                article.setId(resultSet.getLong("id"));
                article.setPostDate(resultSet.getString("post_date"));
                article.setCategory(category);
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setDescription(resultSet.getString("description"));
                news.add(article);
            }
            statement.close();
        }catch (Exception e){

        }
        return news;
    }

    public static News getOneNews(Long id){
        News news = new News();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT article.id, article.post_date, article.category_id, " +
                            "article.title, article.content, article.description, cat.cat_id, " +
                            "cat.cat_name " +
                            "FROM content article INNER JOIN news_categories cat ON article.category_id = cat.cat_id " +
                            "WHERE id = ?");
            statement.setLong(1,id);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getLong("cat_id"));
                category.setName(resultSet.getString("cat_name"));

                news.setId(resultSet.getLong("id"));
                news.setPostDate(resultSet.getString("post_date"));
                news.setCategory(category);
                news.setTitle(resultSet.getString("title"));
                news.setContent(resultSet.getString("content"));
                news.setDescription(resultSet.getString("description"));
            }
            statement.close();
        }catch (Exception e){

        }
        return news;
    }

    public static Category getCategory(Long id){
        Category category = new Category();

        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM news_categories WHERE cat_id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();



            if(resultSet.next()){
               category.setId(resultSet.getLong("cat_id"));
               category.setName(resultSet.getString("cat_name"));
            }
            statement.close();
        }catch (Exception e){

        }
        return category;
    }

    public static ArrayList<Comments> getALlComments (Long id){
        ArrayList<Comments> comments = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT com.comment, com.post_date, com.news_id, us.id, us.full_name " +
                            "FROM comments com " +
                            "INNER JOIN users us ON com.user_id = us.id " +
                            "WHERE com.news_id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setFullName(resultSet.getString("full_name"));

                Comments comment = new Comments();
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getString("post_date"));
                comment.setUser(user);
                comment.setNewsId(resultSet.getInt("news_id"));

                comments.add(comment);
            }

            statement.close();

        }catch (Exception e){

        }
        return comments;
    }

    public static void addNews(News news){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO content (category_id, title, content, description) " +
                            "VALUES (?,?,?,?)");
            statement.setLong(1,news.getCategory().getId());
            statement.setString(2,news.getTitle());
            statement.setString(3,news.getContent());
            statement.setString(4,news.getDescription());
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){

        }
    }

    public static void addComment(Comments comments){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO comments (comment, user_id, news_id) " +
                            "VALUES (?,?,?)");
            statement.setString(1,comments.getComment());
            statement.setInt(2, comments.getUser().getId());
            statement.setInt(3,comments.getNewsId());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){

        }
    }

    public static void addUser(User user){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (email, password, full_name, role_id) " +
                    "VALUES (?,?,?,?)");
            statement.setString(1,user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3,user.getFullName());
            statement.setInt(4,user.getRole());
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){

        }
    }

    public static User getUser(String email){
        User user = null;
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM users WHERE email = ?");
            statement.setString(1,email);

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
                user.setRole(resultSet.getInt("role_id"));
            }
            statement.close();
        }catch (Exception e){

        }


        return user;
    }

    public static void updateNews(News news){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE content SET category_id = ?, title = ?, content = ?, " +
                            "description = ? WHERE id = ?");
            statement.setLong(1, news.getCategory().getId());
            statement.setString(2,news.getTitle());
            statement.setString(3,news.getContent());
            statement.setString(4,news.getDescription());
            statement.setLong(5,news.getId());
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){

        }
    }

    public static void deleteNews(Long id){
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM content where id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){

        }
    }
}
