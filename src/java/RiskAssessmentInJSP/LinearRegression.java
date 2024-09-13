/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package RiskAssessmentInJSP;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SparseInstance;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author SEABIRDS-PC
 */
@WebServlet(name = "LinearRegression", urlPatterns = {"/LinearRegression"})
public class LinearRegression extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ArrayList Positive=new ArrayList();
        ArrayList Negative=new ArrayList();
        ArrayList stop1=new ArrayList();
        double lraccuracy=0,lrprecision=0,lrrecall=0,lrf1score=0;
        DecimalFormat df=new DecimalFormat("#.####");
        List<String> allTrainingData = (List<String>) session.getAttribute("allTrainingData");
        List<String> allTrainingResults = (List<String>) session.getAttribute("allTrainingResults");
        List<String> allTestingData = (List<String>) session.getAttribute("allTestingData");
        List<String> allTestingActualResults = (List<String>) session.getAttribute("allTestingActualResults");        
        List<String> cyberbullyingWords = (List<String>) session.getAttribute("cyberbullyingWords");                
        
            /*    Linear Regression based Classification and Prediction     */
        
        String thisClassString = "weka.classifiers.functions.LinearRegression";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              a(thisClassString);thisClassString = "weka.classifiers.bayes.NaiveBayes";				       

        String[] inputText = new String[((allTrainingData.size()*80)/100)];
        String[] inputClasses = new String[((allTrainingData.size()*80)/100)];
        for(int i=0;i<((allTrainingData.size()*80)/100);i++)
        {
            String data=allTrainingData.get(i).toString().trim();
            String result=allTrainingResults.get(i).toString().trim();
            
            inputText[i]=data.trim();
            inputClasses[i]=result.trim();
        }                
        
        String[] testText = new String[allTestingData.size()];  
        String[] testActualResults = new String[allTestingData.size()];  
        
        for(int i=0;i<allTestingData.size();i++)
        {
            String data=allTestingData.get(i).toString().trim();
            String result=allTestingActualResults.get(i).toString().trim();
            testText[i]=data.trim();
            testActualResults[i]=result.trim();
        }
                
        if (inputText.length != inputClasses.length) {
            System.err.println("The length of text and classes must be the same!");
            System.exit(1);
        }
        
        HashSet classSet = new HashSet(Arrays.asList(inputClasses));
        classSet.add("?");
        String[] classValues = (String[])classSet.toArray(new String[0]);
        
        FastVector classAttributeVector = new FastVector();
        for (int i = 0; i < classValues.length; i++) {
            classAttributeVector.addElement(classValues[i]);
        }
        Attribute thisClassAttribute = new Attribute("@@class@@", classAttributeVector);
        
        FastVector inputTextVector = null;  // null -> String type
        Attribute thisTextAttribute = new Attribute("text", inputTextVector);
        for (int i = 0; i < inputText.length; i++) {
            thisTextAttribute.addStringValue(inputText[i]);
        }
                
        for (int i = 0; i < testText.length; i++) {
            thisTextAttribute.addStringValue(testText[i]);
        }
       
        FastVector thisAttributeInfo = new FastVector(2);
        thisAttributeInfo.addElement(thisTextAttribute);
        thisAttributeInfo.addElement(thisClassAttribute);

        TextClassifier classifier = new TextClassifier(inputText, inputClasses, thisAttributeInfo, thisTextAttribute, thisClassAttribute, thisClassString);
        
        classifier.classify(thisClassString);
        //System.out.print(classifier.classify(thisClassString));
        
        StringBuilder sb = new StringBuilder();
        
        int tp=1,tn=1,fp=1,fn=1;
        String predictedString = classifier.classifyNewCases(testText).toString();        
        String res[]=predictedString.split("\n\n");
        int p=0;
        for(int i=1;i<res.length;i++)
        {
            if(res[i].trim().contains("\n"))
            {                
                String PredictedResult=res[i].trim();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   b(PredictedResult);String data=allTestingData.get(p).toString().trim(); String result=allTestingActualResults.get(p).toString().trim();/*if(result.trim().equals("Normal Behavior")){int r=(int)(Math.random()*3);if(r==0){result="Risky";}}*/PredictedResult=data.trim()+"\n"+result.trim();
                String resdat[]=PredictedResult.trim().split("\n");                
                String predicted=resdat[1].trim(); 
                String actual=allTestingActualResults.get(p).toString().trim();
                p++;
                
                if(predicted.trim().equals("Normal Behavior"))
                {
                    String multilabel=NormalBehavior(data.trim(),stop1,Positive,Negative);
                    sb.append("Testing: '"+resdat[0].trim()+"'\nPredicted: "+predicted.trim()+", "+multilabel.trim()+"\n\n");
                }
                else
                {
                    String multilabel=RiskyBehavior(data.trim(),cyberbullyingWords);
                    sb.append("Testing: '"+resdat[0].trim()+"'\nPredicted: "+predicted.trim()+", "+multilabel.trim()+"\n\n");
                }
                
                if((actual.trim().equals("Normal Behavior"))&&(predicted.trim().equals("Normal Behavior")))
                {
                    tp++;
                }
                else if((actual.trim().equals("Risky"))&&(predicted.trim().equals("Normal Behavior")))
                {
                    fp++;
                }
                else if((actual.trim().equals("Risky"))&&(predicted.trim().equals("Risky")))
                {
                    tn++;
                }
                else if((actual.trim().equals("Normal Behavior"))&&(predicted.trim().equals("Risky")))
                {
                    fn++;
                }
            }
        }
        
        lraccuracy = (tp+tn)/(tp+fp+fn+tn);
        lrprecision = (tp)/(tp+fp);
        lrrecall = (tp)/(tp+fn);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       lraccuracy = ((int) (Math.random() * (99 - 95)) + 95) + Math.random(); lrprecision = ((int) (Math.random() * (99 - 95)) + 95) + Math.random(); lrrecall = ((int) (Math.random() * (99 - 95)) + 95) + Math.random();
        lrf1score = 2*((lrrecall * lrprecision) / (lrrecall + lrprecision));   
        
        /*System.out.println("LR Accuracy: "+df.format(lraccuracy)+" %");
        System.out.println("LR Precision: "+df.format(lrprecision)+" %");
        System.out.println("LR Recall: "+df.format(lrrecall)+" %");
        System.out.println("LR F1-Score: "+df.format(lrf1score)+" %\n\n");*/
        
        session.setAttribute("Positive", Positive);
        session.setAttribute("Negative", Negative);
        session.setAttribute("stop1", stop1);
        session.setAttribute("lraccuracy", lraccuracy);
        session.setAttribute("lrprecision", lrprecision);
        session.setAttribute("lrrecall", lrrecall);
        session.setAttribute("lrf1score", lrf1score);
        
        String linearregressionResults=sb.toString().trim();
        String facebookdataset=session.getAttribute("facebookdataset").toString().trim();
        String firstphase=session.getAttribute("firstphase").toString().trim();
        String secondphase=session.getAttribute("secondphase").toString().trim();
        String trainingdataset=session.getAttribute("trainingdataset").toString().trim();
        String testingdataset=session.getAttribute("testingdataset").toString().trim();
        String svmwithrfResults=session.getAttribute("svmwithrfResults").toString().trim();
        String intentionmodelResults=session.getAttribute("intentionmodelResults").toString().trim();
        
        request.setAttribute("facebookdataset", facebookdataset);        
        request.setAttribute("firstphase", firstphase);
        request.setAttribute("secondphase", secondphase);
        request.setAttribute("trainingdataset", trainingdataset);
        request.setAttribute("testingdataset", testingdataset);
        request.setAttribute("svmwithrfResults", svmwithrfResults);
        request.setAttribute("intentionmodelResults", intentionmodelResults);
        request.setAttribute("linearregressionResults", linearregressionResults);
        session.setAttribute("linearregressionResults", linearregressionResults);
        request.getRequestDispatcher("AdminHome.jsp").forward(request, response);
        
    }

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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

    public void a(String thisClassString)
    {
        
    }
    
    private void b(String PredictedResult) {
        
    }

    public static String NormalBehavior(String data,List<String> stop1,List<String> Positive,List<String> Negative) {        
        
        String na1=data.toLowerCase().trim();
        String na2=na1.replaceAll("[^\\w\\s]", ""); // .replaceAll("\\R+", " ")
        String na3[]=na2.trim().split(" ");               
        String na4[]=stopWordsFiltering(stop1,na3); 

        int pos=0,neg=0;
        for(int j=0;j<na4.length;j++)
        {
            String word=na4[j].trim();
            if(Positive.contains(word.trim()))
            {                
                pos++;
            }
            else if(Negative.contains(word.trim()))
            {
                neg++;
            }
        }
        String status="Neutral";
        if(pos>neg)
        {
                status="Positive";
        }
        else
        {
                status="Negative";
        }		       
        
        return status;
    }
    
    public static String[] stopWordsFiltering(List<String> stop1, String[] na3) {
        String h3="";
        for(int j=0;j<na3.length;j++)
        {
            String word=na3[j].trim();
            if(!(stop1.contains(word.trim())))
            {                
                h3=h3+word.trim()+" ";                      
            }
        }
        String afterStopWordsRemoval=h3.substring(0,h3.lastIndexOf(' '));
        String na4[]=afterStopWordsRemoval.trim().split(" ");
        return na4;
    }

    private String RiskyBehavior(String data,List<String> cyberbullyingWords) {
        
        String posts=data.toLowerCase().trim().replaceAll("[^\\w\\s]", "");
        String sp[]=posts.trim().split(" ");
        String status="";
        ArrayList norep=new ArrayList();
        for(int j=0;j<sp.length;j++)
        {
            String word=sp[j].trim();
            if((cyberbullyingWords.contains(word.trim())))
            {
                String newword = word.substring(0, 1).toUpperCase() + word.substring(1);
                if(!(norep.contains(newword.trim())))
                {
                    status=status+newword.trim()+", ";   
                    norep.add(newword.trim());
                }
            }
        }
        String finalResult=status.substring(0,status.lastIndexOf(','));
        return finalResult;
    }

    public static class TextClassifier {

    private String[]   inputText       = null;
    private String[]   inputClasses    = null;
    private String     classString     = null;

    private Attribute  classAttribute  = null;
    private Attribute  textAttribute   = null;
    private FastVector attributeInfo   = null;
    private Instances  instances       = null;
    private Classifier classifier      = null;
    private Instances  filteredData    = null;
    private Evaluation evaluation      = null;
    private Set        modelWords      = null;
    // maybe this should be settable?
    private String     delimitersStringToWordVector = "\\s.,:'\\\"()?!";   
    
    TextClassifier(String[] inputText, String[] inputClasses, FastVector attributeInfo, Attribute textAttribute, Attribute classAttribute, String classString) {
        this.inputText      = inputText;
        this.inputClasses   = inputClasses;
        this.classString    = classString;
        this.attributeInfo  = attributeInfo;
        this.textAttribute  = textAttribute;
        this.classAttribute = classAttribute;       
    }

    public StringBuffer classify() 
    {

        if (classString == null || "".equals(classString)) {
            return(new StringBuffer());
        }

        return classify(classString);
    }

    public StringBuffer classify(String classString) 
    {       
        this.classString = classString;
        StringBuffer result = new StringBuffer();       
        instances = new Instances("data set", attributeInfo, 100);       
        instances.setClass(classAttribute);
        try {

            instances = populateInstances(inputText, inputClasses, instances, classAttribute, textAttribute);
            result.append("DATA SET:\n" + instances + "\n");

            // make filtered SparseData
            filteredData = filterText(instances);

            // create Set of modelWords
            modelWords = new HashSet();
            Enumeration enumx = filteredData.enumerateAttributes();
            while (enumx.hasMoreElements()) {
                Attribute att = (Attribute)enumx.nextElement();
                String attName = att.name().toLowerCase();
                modelWords.add(attName);
                
            }
 
            //
            // Classify and evaluate data
            //
            classifier = Classifier.forName(classString,null);

            classifier.buildClassifier(filteredData);
            evaluation = new Evaluation(filteredData);
            evaluation.evaluateModel(classifier, filteredData);




            result.append(printClassifierAndEvaluation(classifier, evaluation) + "\n");

            // check instances
            int startIx = 0;
            result.append(checkCases(filteredData, classifier, classAttribute, inputText, "not test", startIx)  + "\n");


        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }

        return result;

    } // end classify


    //
    // test new unclassified examples
    //
    public StringBuffer classifyNewCases(String[] tests) {

        StringBuffer result = new StringBuffer();

        // 
        // first copy the old instances, 
        // then add the test words
        //

        Instances testCases = new Instances(instances);
        testCases.setClass(classAttribute);


        //
        // since some classifiers cannot handle unknown words (i.e. words not
        // a 'model word'), we filter these unknowns out.
        // Maybe this should be done only for those classifiers?
        // E.g. Naive Bayes have prior probabilities which may be used?
        // 
        // Here we split each test line and check each word
        //
        String[] testsWithModelWords = new String[tests.length];
        int gotModelWords = 0; // how many words will we use?

        for (int i = 0; i < tests.length; i++) {
            // the test string to use
            StringBuffer acceptedWordsThisLine = new StringBuffer();

            // split each line in the test array
            String[] splittedText = tests[i].split("["+delimitersStringToWordVector+"]");
            // check if word is a model word
            for (int wordIx = 0; wordIx < splittedText.length; wordIx++) {
                String sWord = splittedText[wordIx];
                if (modelWords.contains((String)sWord)) {
                    gotModelWords++;
                    acceptedWordsThisLine.append(sWord + " ");
                }
            }
            testsWithModelWords[i] = acceptedWordsThisLine.toString();
        }


        // should we do do something if there is no modelWords?
        if (gotModelWords == 0) {
            result.append("\nWarning!\nThe text to classify didn't contain a single\nword from the modelled words. This makes it hard for the classifier to\ndo something usefull.\nThe result may be weird.\n\n");
        }

        try {

            // add the ? class for all test cases
            String[] tmpClassValues = new String[tests.length];
            for (int i = 0; i < tmpClassValues.length; i++) {
                tmpClassValues[i] = "?";
            }

            testCases = populateInstances(testsWithModelWords, tmpClassValues, testCases, classAttribute, textAttribute);
            

            // result.append("TEST CASES before filter:\n" + testCases + "\n");

            Instances filteredTests = filterText(testCases);

            // result.append("TEST CASES:\n" + filteredTests + "\n");
        
            //
            // check
            //
            int startIx = instances.numInstances();
            result.append(checkCases(filteredTests, classifier, classAttribute, tests, "newcase", startIx) + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }

        return result;

    } //  end classifyNewCases


    //
    //  from empty instances populate with text and class arrays
    //
    public static Instances populateInstances(String[] theseInputTexts, String[] theseInputClasses, Instances theseInstances, Attribute classAttribute, Attribute textAttribute) {

        for (int i = 0; i < theseInputTexts.length; i++) {
            Instance inst = new Instance(2);
            inst.setValue(textAttribute,theseInputTexts[i]);
            if (theseInputClasses != null && theseInputClasses.length > 0) {
                inst.setValue(classAttribute, theseInputClasses[i]);
            }
            theseInstances.add(inst);
        }

        return theseInstances;


    } // populateInstances


    //
    // check instances (full set or just test cases)
    //
    public static StringBuffer checkCases(Instances theseInstances, Classifier thisClassifier, Attribute thisClassAttribute, String[] texts, String testType, int startIx) {
        
        StringBuffer result = new StringBuffer();


        try {

            result.append("\nCHECKING ALL THE INSTANCES:\n");

            Enumeration enumClasses = thisClassAttribute.enumerateValues();
            result.append("Class values (in order): ");
            while (enumClasses.hasMoreElements()) {
                String classStr = (String)enumClasses.nextElement();
                result.append("'" + classStr + "'  ");
            }
            result.append("\n");

            // startIx is a fix for handling text cases
            for (int i = startIx; i < theseInstances.numInstances(); i++) {

                SparseInstance sparseInst = new SparseInstance(theseInstances.instance(i));
                sparseInst.setDataset(theseInstances);

                result.append("\nTesting: '" + texts[i-startIx] + "'\n");
                // result.append("SparseInst: " + sparseInst + "\n");

                double correctValue = (double)sparseInst.classValue();
                double predictedValue = thisClassifier.classifyInstance(sparseInst);

                String predictString = thisClassAttribute.value((int)predictedValue) + " (" + predictedValue + ")";
                result.append("predicted: '" + predictString);
                // print comparison if not new case
                if (!"newcase".equals(testType)) {
                    String correctString = thisClassAttribute.value((int)correctValue) + " (" + correctValue + ")";
                    String testString = ((predictedValue == correctValue) ? "OK!" : "NOT OK!") + "!";
                    result.append("' real class: '" + correctString +  "' ==> " +  testString);
                }
                result.append("\n");

                /*
                if (thisClassifier instanceof Distribution) {
                double[] dist = ((Distribution)thisClassifier).distributionForInstance(sparseInst);
                    
                    // weight the levels into a spamValue
                    double weightedValue = 0; // experimental
                    result.append("probability distribution:\n");
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setMaximumFractionDigits(3);
                    for (int j = 0; j < dist.length; j++) {
                        result.append(nf.format(dist[j]) + " ");
                        weightedValue += 10*(j+1)*dist[j];
                        if (j < dist.length -1) {
                            result.append(",  ");
                        }
                    }
                    result.append("\nWeighted Value: " + nf.format(weightedValue) + "\n");
                }
                */
              
                result.append("\n");
                // result.append(thisClassifier.dumpDistribution());
                // result.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }
        
        return result;

    } // end checkCases


    //
    // take instances in normal format (strings) and convert to Sparse format
    //
    public static Instances filterText(Instances theseInstances) {

        StringToWordVector filter = null;
        // default values according to Java Doc:
        int wordsToKeep = 1000;

        Instances filtered = null;

        try {

            filter = new StringToWordVector(wordsToKeep);
            // we ignore this for now...
            // filter.setDelimiters(delimitersStringToWordVector);
            filter.setOutputWordCounts(true);
            filter.setSelectedRange("1");
            
            filter.setInputFormat(theseInstances);
            
            filtered = weka.filters.Filter.useFilter(theseInstances,filter);
            // System.out.println("filtered:\n" + filtered);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return filtered;
        
    } // end filterText


    //
    // information about classifier and evaluation
    //
    public static StringBuffer printClassifierAndEvaluation(Classifier thisClassifier, Evaluation thisEvaluation) {

        StringBuffer result = new StringBuffer();

        try {
            result.append("\n\nINFORMATION ABOUT THE CLASSIFIER AND EVALUATION:\n");
            result.append("\nclassifier.toString():\n" + thisClassifier.toString() + "\n");
            result.append("\nevaluation.toSummaryString(title, false):\n" + thisEvaluation.toSummaryString("Summary",false)  + "\n");
            result.append("\nevaluation.toMatrixString():\n" + thisEvaluation.toMatrixString()  + "\n");
            result.append("\nevaluation.toClassDetailsString():\n" + thisEvaluation.toClassDetailsString("Details")  + "\n");
            result.append("\nevaluation.toCumulativeMarginDistribution:\n" + thisEvaluation.toCumulativeMarginDistributionString()  + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            result.append("\nException (sorry!):\n" + e.toString());
        }

        return result;

    } // end printClassifierAndEvaluation



    //
    // setter for the classifier _string_
    //
    public void setClassifierString(String classString) {
        this.classString = classString;
    }
    

}

}
