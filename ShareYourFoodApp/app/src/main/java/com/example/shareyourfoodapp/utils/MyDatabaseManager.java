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
import java.sql.Driver;
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

        insertData(c, "insert into Recipe values(2, 'juanmanu@ucm.es','Sushi', 'https://www.hogarmania.com/archivos/201104/sushi-a-mi-manera-xl-668x400x80xX.jpg' ,'Ingredientes (4 personas):\n" +
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

        insertData(c, "insert into Recipe values(3, 'admin@ucm.es','Sopa Castellana', 'https://www.hogarmania.com/archivos/201701/5914-1-sopa-castellana-1300-xl-668x400x80xX.jpg' , 'Ingredientes (4 personsas):\n" +
                "4 huevos\n" +
                "250 gr de pan español (de víspera)\n" +
                "250 gr de jamón\n" +
                "250 gr de chorizo\n" +
                "8 dientes de ajo\n" +
                "1 cucharada de pimentón\n" +
                "aceite de oliva virgen extra\n" +
                "sal\n" +
                "perejil\n" +
                "Elaboración:\n" +
                "Pela los ajos, lamínalos y rehógalos en una cazuela grande. Retira la piel del chorizo, córtalo en cuartos y añádelos. Pica el jamón en dados y agrégalos.\n" +
                "\n" +
                "Con ayuda de un cuchillo de sierra, corta el pan e incorpóralo. Rehoga todo bien. Añade el pimentón y remueve todo un poco.\n" +
                "\n" +
                "Cubre con 2 litros de agua y cuece todo conjuntamente durante 20 minutos. Pon a punto de sal.\n" +
                "\n" +
                "Reparte la sopa en 4 cuencos soperos de barro. Casca 1 huevo en cada uno y colócalos en la placa de horno. Hornea a 180ºC durante unos 10 minutos aproximadamente. Sirve y adorna con unas hojas de perejil. ');");

        insertData(c, "insert into Recipe values(4, 'samuelete01@ucm.es','Pulpo a la gallega', 'https://www.hogarmania.com/archivos/201412/5480-1-pulpo-a-la-gallega-865-xl-668x400x80xX.jpg' , 'Ingredientes (4 personsas):\n" +
                "1 pulpo\n" +
                "4 patatas\n" +
                "aceite de oliva virgen extra\n" +
                "sal Maldon\n" +
                "pimentón (dulce y picante)\n" +
                "Elaboración:\n" +
                "Calienta abundante agua en una cazuela grande. Cuando el agua rompa a hervir, hay que asustar el pulpo. Para ello cógelo por la cabeza y mete y sácalo 3 veces. De esta manera conseguirás que quede tieso y no se le caiga la piel durante la cocción.\n" +
                "\n" +
                "Introduce el pulpo y cuécelo durante 40 minutos desde el momento en que empiece a hervir el agua de nuevo. Deja que repose durante 4 minutos en el agua de la cocción. Escurre bien.\n" +
                "\n" +
                "Lava las patatas, córtalas por la mitad y cuécelas durante 20 minutos. También se pueden cocer en la misma agua de cocción del pulpo, pero en tal caso, quedarían teñidas por el agua del pulpo. Pela las patatas y córtalas en 4.\n" +
                "\n" +
                "Corta el pulpo con unas tijeras de cocina en trozos de 1-2 centímetros. Sírvelo con las patatas en platos de madera. Riega con un buen chorro de aceite de oliva, espolvorea con pimentón y sazona con la sal Maldon.');");

        insertData(c, "insert into Recipe values(5, 'jmoffatt@ucm.es','Tarta de queso', 'https://www.hogarmania.com/archivos/202104/tarta-de-queso-vasca-la-vina-receta-karlos-arguinano-karl67410321-668x400x80xX-1.jpg' , 'Ingredientes (6 personsas):\n" +
                "600 g de queso crema\n" +
                "4 huevos pequeños\n" +
                "200 g de azúcar\n" +
                "20 g de harina\n" +
                "250 ml de nata líquida\n" +
                "hojas de menta\n" +
                "Elaboración :\n" +
                "Casca los huevos a un bol y rómpelos con una varilla manual. Agrega el azúcar y la harina, y mezcla un poco. Incorpora el queso crema a temperatura ambiente (sin batir) y sigue mezclando.\n" +
                "Finalmente vierte la nata líquida y mezcla hasta conseguir una masa homogénea.\n" +
                "Cubre un molde desmontable (de 18 centímetros de diámetro) con papel sulfurizado o papel vegetal para horno. Vierte dentro la mezcla y hornéala a 200º durante 40-45 minutos.\n" +
                "\n" +
                "Retira la tarta de queso del horno y deja reposar (3-4 horas) hasta que se enfríe bien antes de desmoldar.\n" +
                "Desmolda la tarta y retírale el papel con cuidado para no romperla.\n" +
                "Sirve la tarta de queso La Viña, córtala en porciones y adorna los platos con unas hojas de menta.');");

        insertData(c, "insert into Recipe values(6, 'samuelete01@ucm.es','Migas', 'https://www.hogarmania.com/archivos/201512/5671-1-migas-manchegas-1056-xl-668x400x80xX.jpg' , 'Ingredientes (4 personsas):\n" +
                "500 gr de pan español\n" +
                "250 ml de agua\n" +
                "8 dientes de ajo\n" +
                "200 g de chorizos frescos\n" +
                "4 lonchas de panceta fresca\n" +
                "1 pimiento choricero\n" +
                "aceite de oliva virgen extra\n" +
                "sal\n" +
                "perejil\n" +
                "Elaboración:\n" +
                "Corta el pan en dados y colócalo en un bol. Añade un poco de sal al agua y vierte sobre el pan. Envuélvelos en un trapo blanco, volteándolo de vez en cuando el paño para que se reparta mejor la humedad. Deja que repose por lo menos durante 2 horas en el frigorífico.\n" +
                "\n" +
                "Corta la panceta en daditos y fríelos en una sartén con un poco de aceite. Cuando se dore, añade el chorizo cortado en rodajas. Retira el tallo y las semillas del pimiento choricero, trocea y añade hasta que se tueste. Escurre.\n" +
                "\n" +
                "Agrega un poco de aceite limpio a la tartera, añade los dientes de ajo enteros y pelados y dóralos un poco. Agrega las migas (humedecidas) y remuévelas constantemente con una cuchara o una pala hasta que queden sueltas y doraditas.\n" +
                "\n" +
                "Agrega el chorizo y la panceta. Mezcla todo bien hasta que el chorizo y la panceta se calienten. Maja el pimiento choricero y añade. Mezcla bien y sirve. Decora con perejil.');");


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

                    mUser = new User(email, password, rs.getString(1));

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
        mUser = new User("","", "");
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
        mUser = new User(email, password, name);

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

    public static ArrayList<Recipe> getMyRecipes(Connection c){
        ArrayList<Recipe> recipes = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs=null;
        try
        {
            ps = c.prepareStatement("select * from Recipe where author_mail = ?");
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

    public static ArrayList<Recipe> getRecipeSearch(Connection c, String txt){
        ArrayList<Recipe> recipes = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs=null;
        try
        {
            ps = c.prepareStatement("select * from Recipe where title like '%"+txt+"%';");
            //ps.setString(1, txt);
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
            ps2 = c.prepareStatement("select MAX(id) from Comment;");
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

    public static Boolean deleteComment(Connection c, Integer idComment) {
        PreparedStatement ps = null;
        ResultSet rs=null;
        try {
            ps = c.prepareStatement("delete from Comment where id = ?");
            ps.setInt(1, idComment);
            ps.executeUpdate();
            ps.close();
        }catch(Exception e) {
            System.out.println("Error en la eliminación.");
            return false;
        }
        return true;
    }


    public static Boolean changeName(Connection c, String newName) {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("update User set name = ? where mail=?");
            ps.setString(1, newName);
            ps.setString(2, mUser.getEmail());
            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println("Error en la actualización.");
            return false;
        }
        mUser.setName(newName);
        return true;
    }

    public static Boolean changePassword(Connection c, String password, String newPassword) {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("update User set password = ? where password=? and mail=?");
            ps.setString(1, newPassword);
            ps.setString(2, password);
            ps.setString(3, mUser.getEmail());
            ps.executeUpdate();
        }catch(Exception e) {
            System.out.println("Error en la actualización.");
            return false;
        }
        mUser.setPassword(newPassword);
        return true;
    }

}
