package com.example.shareyourfoodapp.utils;

import android.content.Context;

import com.example.shareyourfoodapp.model.Comment;
import com.example.shareyourfoodapp.model.Recipe;
import com.example.shareyourfoodapp.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyDatabaseManager {
    public static Connection mConnection;
    public static User mUser;

    public static void initialize(Connection c){
        createTable(c,"create table if not exists User (mail varchar2(30) primary key, password varchar2(20) not null, name varchar2(20) not null);");
        insertData(c, "insert into User values('admin@ucm.es','admin','admin')");
        insertData(c, "insert into User values('johanc01@ucm.es','admin','Johan');");
        insertData(c, "insert into User values('jmoffatt@ucm.es', 'password', 'Julian');");
        insertData(c, "insert into User values('juanmanu@ucm.es', 'password2', 'Juanma');");
        insertData(c, "insert into User values('samuelete01@ucm.es', 'madrid28935', 'Samuel');");

        createTable(c,"create table if not exists Recipe (id int(3) primary key, author_mail varchar2(30), title varchar2(20), image_url varchar2(100), description varchar2(10000), constraint fk foreign key (author_mail) references User (mail));");
        insertData(c,"insert into Recipe values(0, 'samuelete01@ucm.es','Bacalao al Pil Pil', 'https://www.hogarmania.com/archivos/201107/bacalao-pilpil-xl-668x400x80xX.jpg' ,'Ingredientes:\n" +
                "Para 4 personas:\n" +
                "4 trozos de bacalao salado\n" +
                "5 dientes de ajo\n" +
                "1/2 guindilla\n" +
                "300 ml. de aceite virgen extra\n" +
                "Elaboración:\n" +
                "Pon el aceite en una cazuela baja y amplia. Pela los ajos y dóralos. Cuando estén dorados, pásalos a un plato y reservalos. Limpia la guindilla y trocéala en 4, saltéalas brevemente y retíralas a un plato.\n" +
                "En el mismo aceite, pon a cocinar los trozos de bacalao (primero con la piel hacia arriba). Fríelos cuatro minutos por cada lado y retíralos a un plato.\n" +
                "El suero que vayan soltando resérvalo en un bol.\n" +
                "\n" +
                "Pil Pil:\n" +
                "Pasa el aceite a otro bol y espera a que se temple el aceite. Cuando esté templado pon un poco de aceite en la cazuela y un poco del suero del bacalao y lígalo con un colador moviendo suavemente. Añade el resto del aceite poco a poco. Cuando hayas añadido la mitad del aceite incorpora el resto del suero sin dejar de mover con el colador. Incorpora el resto del aceite, sigue moviendo hasta que quede un pil pil espeso. Incorpora las tajadas de bacalao y calienta bien.');");

        insertData(c, "insert into Recipe values(1, 'johanc01@ucm.es','Ensalada Rusa', 'https://www.hogarmania.com/archivos/201906/ensaladilla-rusa-xl-668x400x80xX.jpg' ,'Ingredientes (4 personas):\n" +
                "2-3 patatas (450 g)\n" +
                "4 zanahorias\n" +
                "2 huevos\n" +
                "20 aceitunas rellenas\n" +
                "3 cucharadas de guisantes en conserva\n" +
                "2 latas de atún en aceite (200 g)\n" +
                "2/4 de mayonesa casera\n" +
                "sal\n" +
                "perejil\n" +
                "Pon las patatas (limpia y con piel) a cocer a fuego suave en una cazuela con agua fría. Pela las zanahorias y añadelas. Déjalas cocer (patatas y zanahorias) durante 25 minutos. A los 25 minutos de cocción,introduce los huevos, 1 cucharada de sal y deja cocer durante 10 minutos más.\n" +
                "Escurre el agua, deja que se temple todo. Reserva las zanahorias en un plato y pela las patatas y el huevo.\n" +
                "Pica la patata y el huevo en daditos. Corta las zanahorias en 4 cuartos a lo largo. Apila los trozos y córtalas perpendicularmente hasta conseguir trocitos pequeños.\n" +
                "Corta las aceitunas por la mitad y después finamente.\n" +
                "Pon la patata, el huevo, la zanahoria y las aceitunas en un cuenco grande, agrega los guisantes y el atún desmigado.Incorpora la mayonesa, mezcla suavemente. Prueba, pon a punto de sal y sirve. Adorna con una rama de perejil.');");

        insertData(c, "insert into Recipe values(2, 'juanmanu@ucm.es','Sushi', 'https://www.hogarmania.com/archivos/201906/ensaladilla-rusa-xl-668x400x80xX.jpg' ,'Ingredientes (4 personas):\n" +
                "4 láminas de alga nori\n" +
                "200 gr de arroz\n" +
                "100 gr de bonito\n" +
                "langostinos\n" +
                "1 loncha de salmón ahumado\n" +
                "2 lonchas de jamón ibérico\n" +
                "4 rodajas finas de pepino\n" +
                "4 rodajas finas de tomate\n" +
                "2 lonchas de aguacate\n" +
                "2 pepinillos\n" +
                "2 dientes de ajo\n" +
                "agua\n" +
                "aceite virgen extra\n" +
                "sal\n" +
                "cebollino\n" +
                "- Para acompañar:\n" +
                "salsa de soja\n" +
                "jengibre marinado\n" +
                "vinagre de arroz\n" +
                "miel.\n" +
                "Elaboración:\n" +
                "Pon a cocer el arroz en una cazuela con agua y dos dientes de ajo y una pizca de sal. Deja cocer durante 18 minutos a fuego medio.\n" +
                "Mientras, en un recipiente cubre el fondo con sal gorda, coloca bonito finamente fileteado y cubre con más sal gorda. Retira la sal del bonito y coloca en otro recipiente cubierto con aceite. Reserva.\n" +
                "Tuesta cada lámina de alga nori en una sartén caliente (vuelta y vuelta)y coloca sobre una esterilla. Cubre con arroz y extiende con una espátula. Posteriormente, distribuye el arroz sobre las algas cuidando dejar una franja de dos centímetros alrededor. Para manipular el arroz se aconseja tener las manos y todos los utensilios mojados en agua fría.\n" +
                "Coloca en uno de los sushis, el salmón y el aguacate. Enrolla el alga ayudado por la esterilla y corta el rollo en rodajas de dos dedos de grosor con un cuchillo bien afilado y mojado en agua. Haz lo mismo con el de bonito con pepino, jamón con tomate y langostinos con pepinillo. Para servir, acompaña los diferentes sushis con salsa de soja, jengibre marinado, miel o vinagre de arroz. ');");

        createTable(c, "create table if not exists Comment (id int(3) primary key, author_mail varchar2(30), id_recipe int (3), text varchar2(300), constraint fk1 foreign key (author_mail) references User (mail), constraint fk2 foreign key (id_recipe) references Recipe (id));");
        insertData(c, "insert into Comment values (0, 'juanmanu@ucm.es', 0, 'Una receta muy buena, la hice esta tarde y mis invitados quedaron muy satisfechos. Recomendable al 64,21%');");
        insertData(c, "insert into Comment values (1, 'jmoffatt@ucm.es', 1, 'Muy buena receta, no soy 100tifico pero me sirve');");
        insertData(c, "insert into Comment values (2, 'samuelete01@ucm.es', 2, 'Muy buena receta, no soy 100tyfiko pero me sirve');");

    }


    public static Connection myConnection(Context context){
        if(mConnection == null){
            mConnection = ConnectionBD("ShareYourFood.db", context);
        }
        return mConnection;
    }


    public static Connection ConnectionBD(String db, Context context) {
        Connection c=null;
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.sqldroid.SQLDroidDriver").newInstance());
        } catch (Exception e) {
            throw new RuntimeException("Failed to register SQLDroidDriver");
        }
        String jdbcUrl = "jdbc:sqldroid:" + "/data/data/" + context.getPackageName() + "/ShareYourFood.db";
        try {
            c = DriverManager.getConnection(jdbcUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return c;
    }

    public static void createTable(Connection c,String sql)  {
        Statement s = null;
        try {
            s = c.createStatement();
            s.executeUpdate(sql);
            s.close();
        }catch(Exception e) {
            System.out.println("Error en la creacion.");
        }
        System.out.println("Creacion con exito!");
    }

    public static void insertData(Connection c, String sql) {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(Exception e) {
            System.out.println("Error en la insercion.");
        }
        System.out.println("Inserciones realizadas!");
    }



// METODOS PROPIOS
    public static Boolean login(Connection c, String email, String password)
    {
        PreparedStatement ps = null;
        ResultSet rs=null;
        try
        {
            ps = c.prepareStatement("select name from User where mail=? and password=?;");
            ps.setString(1, email);
            ps.setString(2, password);
            rs= ps.executeQuery();
            if(rs.next()){
                do
                {

                    mUser = new User(email, rs.getString(1));

                }while(rs.next());
                ps.close();
                return true;
            }
            else{
                ps.close();
                return false;
            }
        }catch(Exception e)  {
            System.out.println("Error en la consulta.");
            return false;
        }

    }

    public static void logOut(){
        mUser = new User("","");
    }

    public static Boolean register(Connection c, String name, String email, String password) {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("insert into User values(?,?,?)");
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println("Error en la insercion.");
            return false;
        }
        mUser = new User(email, name);

        return true;
    }

    public static ArrayList<Recipe> getRecipes(Connection c){
        ArrayList<Recipe> recipes = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs=null;
        try
        {
            ps = c.prepareStatement("select * from Recipe where author_mail != ?");
            ps.setString(1, mUser.getEmail());
            rs= ps.executeQuery();
            while(rs.next()) {
                Recipe recipe = new Recipe(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5));

                recipes.add(recipe);
            }
            ps.close();
        }catch(Exception e)  {
            System.out.println("Error en la consulta.");
        }

        return recipes;
    }

    public static ArrayList<Comment> getComments(Connection c, Integer idRecipe){
        ArrayList<Comment> comments = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs=null;
        try
        {
            ps = c.prepareStatement("select * from Comment where id_recipe = ?");
            ps.setInt(1, idRecipe);

            rs= ps.executeQuery();
            while(rs.next()) {
                Comment comment = new Comment(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4));

                comments.add(comment);
            }
            ps.close();
        }catch(Exception e)  {
            System.out.println("Error en la consulta.");
        }

        return comments;
    }

    public static Boolean addComment(Connection c, Integer idRecipe, String text) {
        PreparedStatement ps = null;

        ResultSet rs=null;
        PreparedStatement ps2 = null;

        try {
            ps2 = c.prepareStatement("select COUNT(*) from Comment;");
            rs = ps2.executeQuery();
            Integer id = -1;
            if(rs.next()){
                id = rs.getInt(1)+1;
            }
            ps2.close();

            ps = c.prepareStatement("insert into Comment values(?,?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, mUser.getEmail());
            ps.setInt(3, idRecipe);
            ps.setString(4, text);
            ps.executeUpdate();
            ps.close();
        }catch(Exception e) {
            System.out.println("Error en la insercion.");
            return false;
        }
        return true;
    }
}
