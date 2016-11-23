package core;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reports.*;

public class Client {

    /** Declaration of the logger
     *  used for debugging purposes
     */
    static Logger logger = LoggerFactory.getLogger("Client");

    // First test
    public static void a1Test() throws InterruptedException, FileNotFoundException, IOException{
        logger.info("Test 1 start");
        Clock clock = new Clock(2000);
        clock.start();
        Clock clock2 = new Clock(2000);
        clock2.start();
        Project proot = new Project("RT","proot",null);
        Project project = new Project("P1","p1",proot);
        Printer printer = new Printer(proot);
        clock2.addObserver(printer);
        proot.addActivity(project);
        Task T3 = new Task("T3","",project);
        project.addActivity(T3);
        Project neew = new Project("P2","",project);
        project.addActivity(neew);
        Task T1 = new Task("T1","",neew);
        Task T2 = new Task("T2","",neew);
        neew.addActivity(T1);
        neew.addActivity(T2);
        Thread.sleep(2000);
        T3.start(clock);
        Thread.sleep(3000);
        T3.stop(clock);
        Thread.sleep(7000);
        T2.start(clock);
        Thread.sleep(10000);
        T2.stop(clock);
        T3.start(clock);
        Thread.sleep(2000);
        T3.stop(clock);
        Thread.sleep(1000);
        clock.deleteObservers();
        clock2.deleteObservers();
        clock.stop();
        clock2.stop();
        logger.info("Test 1 stop");
        logger.info("Test serialize start save");
        SerialSave guardar = new SerialSave();
        guardar.Save(proot);
        logger.info("Test serialize end save");
    }

    //Second test
    public static void a2Test() throws InterruptedException{
        logger.info("Test 2 start");
        Clock clock = new Clock(2000);
        clock.start();
        Clock clock2 = new Clock(2000);
        clock2.start();
        Project proot = new Project("RT","proot",null);
        Printer printer = new Printer(proot);
        clock2.addObserver(printer);
        Project project = new Project("P1","p1",proot);
        proot.addActivity(project);
        Task T3 = new Task("T3","",project);
        project.addActivity(T3);
        Project neew = new Project("P2","",project);
        project.addActivity(neew);
        Task T1 = new Task("T1","",neew);
        Task T2 = new Task("T2","",neew);
        neew.addActivity(T1);
        neew.addActivity(T2);
        Thread.sleep(2000);
        T3.start(clock);
        Thread.sleep(4000);
        T2.start(clock);
        Thread.sleep(2000);
        T3.stop(clock);
        Thread.sleep(2000);
        T1.start(clock);
        Thread.sleep(4000);
        T1.stop(clock);
        Thread.sleep(2000);
        T2.stop(clock);
        Thread.sleep(4000);
        T3.start(clock);
        Thread.sleep(2000);
        T3.stop(clock);
        Thread.sleep(1000);
        clock.deleteObservers();
        clock2.deleteObservers();
        clock.stop();
        clock2.stop();  
        logger.info("Test 2 stop");
    }

    //Test for serialize
    public static void serializeTest() throws FileNotFoundException, ClassNotFoundException, IOException, InterruptedException{
        logger.info("Test serialize start");
        SerialSave cargar = new SerialSave();
        Activity root = cargar.Load();
        Printer printer = new Printer(root);
        Clock clock = new Clock(1000);
        clock.start();
        clock.addObserver(printer);
        Thread.sleep(2000);
        clock.deleteObservers();    
        clock.stop();
        logger.info("Test serialize stop");
    }
    
    public static Date stringToDate(String doteInString) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date parsedDate = formatter.parse(doteInString);
        return parsedDate;
    }
    
    public static String getDateAsStringFormated(Date dateToFormat){
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateToFormat);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        return String.format(("%02d-%02d-%d-%d_%02d_%02d"),day,month,year,hour,minute,second);
    }
    
    public static void reportTestStatic() throws ParseException, IOException, InterruptedException{
        
        
        Project R = new Project("RT","proot",null);        
        Project P1 = new Project("P1","p1",R);
        R.addActivity(P1);
        Task T3 = new Task("T3","",P1);
        P1.addActivity(T3);
        Project P2 = new Project("P2","",P1);
        P1.addActivity(P2);
        Task T1 = new Task("T1","",P2);
        Task T2 = new Task("T2","",P2);
        P2.addActivity(T1);
        P2.addActivity(T2);
        
        //    R
        //      \
        //      P1
        //     /  \
        //   P2    T3
        //  /  \
        // T1  T2
        //
        // 15/11/2016 9:00:00 -> 17/11/2016 10:00:00
        
        Periode periodeT1 = new Periode(stringToDate("16/11/2016 12:00:00"),stringToDate("16/11/2016 13:00:00"));
        T1.setPeriode(periodeT1);
        Periode periodeT2 = new Periode(stringToDate("16/11/2016 10:00:00"),stringToDate("16/11/2016 14:00:00"));
        T2.setPeriode(periodeT2);
        Periode periodeP2 = new Periode(stringToDate("16/11/2016 10:00:00"),stringToDate("16/11/2016 14:00:00"));
        P2.setPeriode(periodeP2);
        Periode periodeT3 = new Periode(stringToDate("15/11/2016 9:00:00"),stringToDate("17/11/2016 10:00:00"));
        T3.setPeriode(periodeT3);
        Periode periodeP1 = new Periode(stringToDate("15/11/2016 9:00:00"),stringToDate("17/11/2016 10:00:00"));
        P1.setPeriode(periodeP1);
        Periode periodeR = new Periode(stringToDate("15/11/2016 9:00:00"),stringToDate("17/11/2016 10:00:00"));
        R.setPeriode(periodeR);
        
        // User Choose
        Date dataIniciUser = stringToDate("16/11/2016 11:00:00");
        Date dataFiUser = stringToDate("17/11/2016 9:00:00");
        
        Periode userPeriode = new Periode(dataIniciUser,dataFiUser);
        ExtendedReport report = new ExtendedReport(userPeriode,R);
        //ShortReport report = new ShortReport(userPeriode,R);
        
        String fileFormat = String.format(("report--%s--%s--(%s).txt"),getDateAsStringFormated(dataIniciUser),getDateAsStringFormated(dataFiUser),getDateAsStringFormated(new Date()));
        ReportTextVisitor visitor = new ReportTextVisitor(fileFormat);
        
        //String fileFormat = String.format(("report--%s--%s--(%s).html"),getDateAsStringFormated(dataIniciUser),getDateAsStringFormated(dataFiUser),getDateAsStringFormated(new Date()));
        //ReportHTMLVisitor visitor = new ReportHTMLVisitor(fileFormat);
        
        report.printReport(visitor);
         
    }
    
public static void reportTestDynamic() throws ParseException, IOException, InterruptedException{
        
        Clock clock = new Clock(1000);
        clock.start();
        Clock clock2 = new Clock(1000);
        clock2.start();
        Project R = new Project("RT","proot",null);
        Printer printer = new Printer(R);
        clock2.addObserver(printer);
        Project P1 = new Project("P1","p1",R);
        R.addActivity(P1);
        Task T3 = new Task("T3","",P1);
        P1.addActivity(T3);
        Project P2 = new Project("P2","",P1);
        P1.addActivity(P2);
        Task T1 = new Task("T1","",P2);
        Task T2 = new Task("T2","",P2);
        P2.addActivity(T1);
        P2.addActivity(T2);
        
        //      R
        //      \
        //      P1
        //     /  \
        //   P2    T3
        //  /  \
        // T1  T2
        
        Thread.sleep(2000);
        T3.start(clock);
        Thread.sleep(4000);
        T2.start(clock);
        Thread.sleep(2000);
        T3.stop(clock);
        Thread.sleep(2000);
        T1.start(clock);
        Thread.sleep(4000);
        T1.stop(clock);
        Thread.sleep(2000);
        T2.stop(clock);
        T3.start(clock);
        Thread.sleep(2000);
        T3.stop(clock);
        Thread.sleep(1000);
        clock.deleteObservers();
        clock2.deleteObservers();
        clock.stop();
        clock2.stop();  
        
        // User Choose
        Date dataIniciUser = stringToDate("15/11/2016 19:30:00");
        Date dataFiUser = stringToDate("25/11/2016 19:40:00");
        
        Periode userPeriode = new Periode(dataIniciUser,dataFiUser);
        ExtendedReport report = new ExtendedReport(userPeriode,R);
        //ShortReport report = new ShortReport(userPeriode,R);

        //String fileFormat = String.format(("report--%s--%s--(%s).txt"),getDateAsStringFormated(dataIniciUser),getDateAsStringFormated(dataFiUser),getDateAsStringFormated(new Date()));
        //ReportTextVisitor visitor = new ReportTextVisitor(fileFormat);
        
        String fileFormat = String.format(("report--%s--%s--(%s).html"),getDateAsStringFormated(dataIniciUser),getDateAsStringFormated(dataFiUser),getDateAsStringFormated(new Date()));
        ReportHTMLVisitor visitor = new ReportHTMLVisitor(fileFormat);
        
        report.printReport(visitor);
        
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        //a1Test();
        //serializeTest();
        //a2Test();

        reportTestStatic();
        //reportTestDynamic();
        
    }

}