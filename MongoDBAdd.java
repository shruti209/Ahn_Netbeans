/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MongoDB;

//import MongoDB.MongoDbFetch.DBConnection;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.parser.JSONParser;
import org.bson.Document;
import org.json.simple.JSONObject;
import static MongoDB.MongoDBAdd.DBConnection;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Aritra Guha Thakurta
 */
@WebServlet(name = "MongoDBAdd", urlPatterns = {"/MongoDBAdd/*"})
public class MongoDBAdd extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Get reached");
        String contextPath = request.getRequestURL().toString();
        System.out.println(request.getPathInfo());
        
        response.setStatus(HttpServletResponse.SC_OK);// Set Status
        // Obtain the otput stream
       //System.out.println(OS.toString());
//        System.out.println(outputStream.toString());
//        // outputStream.write();// Return the response
//        outputStream.flush();// Flush the response
//        outputStream.close();// Close the response
        UserInfo u = new UserInfo();
        Track t = new Track();  
        
//        PrintWriter out = response.getWriter();
//        String r=request.getPathInfo().toString().substring(1);
//        int t1=Integer.valueOf(r);
//        out.println(t1*2);
        
        
        // Get the response and convert it into a string
        String check="AddUser,Fname,LastName,Email@gmail.com,353258hsagdaskdajaakaskksakdjkaskdsa,Doctor";
        List<String> StringParse = Arrays.asList(check.split(","));
        if (StringParse.get(0).equals("AddUser")){
        // Parse to obtain the userID, First Name, Last Name, email, password, role
        String UserID = StringParse.get(0);
        String First_Name = StringParse.get(1);
        String Last_Name = StringParse.get(2);
        String email = StringParse.get(3);
        String password = StringParse.get(4);
        String role = StringParse.get(5);
        
        u.setFName(First_Name);
        u.setLName(Last_Name);
        u.setEmail(email);
        u.setPassword(password);
        u.setRole(role);
        
        AddUser(u);
        }
        else if (StringParse.get(0).equals("AddTrack")){
        
        //Example for you Sid    
        String check2="AddTrack,Email@gmail.com,01/01/2017,12:00AM";
        // Parse to obtain the userID, First Name, Last Name, email, password, role
        String UserID1 = StringParse.get(0);
        String Date = StringParse.get(1);
        String Time = StringParse.get(2);
         
        t.getuserID();
        t.getTime();
        
        AddTrack(t);
        }
      
       
        
   
        

          //connect to the database
//            out.println("line 48"); 
//            response.setContentType("application/json");
//        Gson gson = new Gson();
//        String s;
//        String[] resultArray;
//        LinkedList<UserInfo> resultList = new LinkedList<>();
//        PrintWriter o = response.getWriter();
//        if ((s = request.getReader().readLine()) != null) {
//            resultArray = s.split("(?<=})");
//            for (String r : resultArray) {
//                UserInfo userObject = gson.fromJson(r, UserInfo.class);
//                resultList.offer(userObject);
//            }

//        }        
    }
    
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response) 
            throws ServletException, IOException {
            doGet(request, response);
            
            
}

    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    public static MongoDatabase DBConnection() {
        //Connect to MongoDB server
        MongoCredential mc = MongoCredential.createCredential("shruti209", "ahn_mobile_app", "ahn12345".toCharArray());
        ServerAddress addr = new ServerAddress("ds249325.mlab.com", 49325);
        MongoClientURI uri = new MongoClientURI("mongodb://user:user@ds249325.mlab.com:49325/ahn_mobile_app");
        MongoClient client = new MongoClient(uri);
        MongoDatabase db = client.getDatabase("ahn_mobile_app");
        return db;
    }

    public void AddUser(UserInfo userInfo){
        MongoDatabase db = DBConnection();
        MongoCollection<Document> user_info = db.getCollection("user_info");
        Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", "1")
                .append("info", new BasicDBObject("First_Name", userInfo.getFName()).append("Last_Name",userInfo.getLName()).append("Email",userInfo.getEmail()).append("Password",userInfo.getPassword()).append("Role", userInfo.getRole()));
        user_info.insertOne(doc);
    }

    public void AddTrack(Track track){
         MongoDatabase db = DBConnection();
         MongoCollection<Document> track_info = db.getCollection("track_info");
         Document doc = new Document("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new BasicDBObject("User_ID",track.getuserID()).append("Time",track.getTime()));
        track_info.insertOne(doc);
    }

//    public void AddTag(Tag tag){
//       MongoDatabase db = DBConnection();
//       MongoCollection<Document> tag_info = db.getCollection("tag_info");
//       Document doc = new Document("name", "MongoDB")
//                .append("type", "database")
//                .append("count", 1)
//                .append("info", new BasicDBObject("Tag_ID", "1245").append("Name","ABC").append("Tag_Type","PWQ" ).append("Threshold","124"));
//        tag_info.insertOne(doc);
//    }

// Fetch Code to be fixed    
    
//   public ArrayList<JSONObject> FetchTrack(){
//        MongoDatabase db = DBConnection();
//        MongoCollection<Document> track_info = db.getCollection("track_info");
//        ArrayList<JSONObject> d1 = new  ArrayList<JSONObject>();
//        Block <Document> printBlock;
//        printBlock = new Block <Document>() {
//            
//            public void apply(final Document document) {
//                JSONParser parser = new JSONParser();
//                String s = document.toJson();
//                
//                try {
//                    JSONObject j = (JSONObject) parser.parse(s);
//                    d1.add(j);
//                } catch (ParseException ex) {
//                    Logger.getLogger(MongoDbFetch.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//            }
//        }
//    }            
//
//        collection.find(eq("User_ID", "Shruti@Gmail.com")).forEach(printBlock);
//        return d1;
//      
//    }
    
    
    public class UserInfo {
        private String user_ID, First_Name, Last_Name, Email, Password, Role;

        UserInfo(){
            this.user_ID = "";
            this.First_Name = "";
            this.Last_Name = "";
            this.Email = "";
            this.Password = "";
            this.Role = "";
        }

        public void setUserID (String user_ID) {
            this.user_ID = user_ID;
        }

        public String getUserID () {
            return this.user_ID;
        }

        public void setFName (String First_Name) {
            this.First_Name = First_Name;
        }

        public String getFName () {
            return this.First_Name;
        }

        public void setLName (String Last_Name) {
            this.Last_Name = Last_Name;
        }

        public String getLName () {
            return this.Last_Name;
        }

        public void setEmail (String Email) {
            this.Email = Email;
        }

        public String getEmail () {
            return this.Email;
        }

        public void setPassword (String Password) {
            this.Password = Password;
        }

        public String getPassword () {
            return this.Password;
        }

        public void setRole (String Role) {
            this.Role = Role;
        }

        public String getRole () {
            return this.Role;
        }


    }

    public class Track{
        private String user_ID, date, Time;

        Track(){
            this.user_ID = user_ID;
            this.date = date;
//            this.tag_ID = tag_ID;
            this.Time = Time;
        }

        public void setuserID (String user_ID) {
            this.user_ID = user_ID;
        }

        public String getuserID () {
            return this.user_ID;
        }
        
        public void setDate (String date) {
            this.date=date;
        }

        public String getDate () {
            return this.date;
        }
        
  

//        public void settagID (String tag_ID) {
//            this.tag_ID = tag_ID;
//        }
//
//        public String gettagID () {
//            return this.tag_ID;
//        }

        public void setTime (String Time) {
            this.Time = Time;
        }

        public String getTime () {
            return this.Time;
        }

    }

//    public class Tag{
//        private String tag_ID, Name, Tag_type, Threshold;
//
//        Tag(){
//            this.tag_ID = tag_ID;
//            this.Name = Name;
//            this.Tag_type = Tag_type;
//            this.Threshold = Threshold;
//        }
//
//        public void setuserID (String tag_ID) {
//            this.tag_ID = tag_ID;
//        }
//
//        public String gettagID () {
//            return this.tag_ID;
//        }
//
//        public void setName (String Name) {
//            this.Name = Name;
//        }
//
//        public String getName () {
//            return this.Name;
//        }
//
//        public void settagtype (String Tag_type) {
//            this.Tag_type = Tag_type;
//        }
//
//        public String gettagtype () {
//            return this.Tag_type;
//        }
//
//        public void setThreshold (String Threshold) {
//            this.Threshold = Threshold;
//        }
//
//        public String getThreshold () {
//            return this.Threshold;
//        }
//
//    }

    
    
}
