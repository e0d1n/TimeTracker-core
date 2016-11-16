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
    	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy/hh:mm:ss");
    	Date parsedDate = formatter.parse(doteInString);
    	return parsedDate;
    }
    
    public static void reportTest() throws ParseException, IOException, InterruptedException{
    	
    	Clock clock = new Clock(1000);
        clock.start();
        Clock clock2 = new Clock(1000);
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
        T3.start(clock);
        Thread.sleep(2000);
        T3.stop(clock);
        Thread.sleep(1000);
        clock.deleteObservers();
        clock2.deleteObservers();
        clock.stop();
        clock2.stop();	
        
        Periode userPeriode = new Periode(stringToDate("15/11/2016/19:30:00"),stringToDate("18/11/2016/19:40:00"));
	    ExtendedReport report = new ExtendedReport(userPeriode,proot);
	    //ShortReport report = new ShortReport(userPeriode,proot);
	    //ReportTextVisitor visitor = new ReportTextVisitor("out.txt");
	    ReportHTMLVisitor visitor = new ReportHTMLVisitor("out.html");
	    report.printReport(visitor);
        
    	
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException, ClassNotFoundException, ParseException {
        //a1Test();
        //serializeTest();
        //a2Test();
        

    	reportTest();
	    
    	
    	/*
    	Periode p1 = new Periode(stringToDate("15/11/2016/19:30:00"),stringToDate("15/11/2016/19:40:00"));
    	p1.print();
    	
    	Periode p2 = new Periode(stringToDate("15/11/2016/19:25:00"),stringToDate("15/11/2016/19:45:00"));
    	
    	p2.print();
    	
    	Periode intersected = p1.intersect(p2);
        
    	System.out.println("Intersect:");
    	if (intersected != null){
    		intersected.print();
    	}else{
    		
    		System.out.println("NO INTERSECT");
    	}
    	*/
    	
    	//tableVisitorTest();
		
    }

}
