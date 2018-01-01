package com.example.chrisantuseze.hadum.Quiz.Categories;

/**
 * Created by CHRISANTUS EZE on 21/11/2017.
 */

public class QuestionsFUTO {
    private String[] mQuestion = {
            "Who designed & built Hadum?",
            "When did Nigeria gain her independence?",
            "Who is the current FUTO VC?",
            "What year was FUTO established?",
            "What's the name of FUTO's Chancellor",
            "Who qualified Nigeria for Russia 2018 by scoring the only goal?",
            "Who became a governor as a result of the courts verdict in his favour?"
    };

    private String[][] mOptions = {
            {"Chris Eze","Bash","Eddie","Babuje"},
            {"1900","1958","1999","1960"},
            {"Prof C.C Eze","Prof J.C Amah","Prof F.C Eze","Prof C.C Asiabaka"},
            {"1947","2001","1988","1991"},
            {"Alhaji Abdulfatai Isah","Chief Imeh Ike","Mohammad Maigari II","Shehu Alero Yusuf"},
            {"Victor Moses","Alex Iwobi","Mikel Obi","Emmanuel Iheanacho"},
            {"Liyel Imoke","Rotimi Amaechi","Abubakar Muazu","Peter Obi"}
    };

    private String[] mAnswer = {
            "Chris Eze","1960","Prof F.C Eze","1988","Shehu Alero Yusuf","Alex Iwobi","Rotimi Amaechi"
    };

    public String getQuestion(int position){
        if (position >= mQuestion.length)
            return "";
        return mQuestion[position];
    }
    public String getChoice1(int position){
        if (position >= mQuestion.length)
            return "";
        return mOptions[position][0];
    }
    public String getChoice2(int position){
        if (position >= mQuestion.length)
            return "";
        return mOptions[position][1];
    }
    public String getChoice3(int position){
        if (position >= mQuestion.length)
            return "";
        return mOptions[position][2];
    }
    public String getChoice4(int position){
        if (position >= mQuestion.length)
            return "";
        return mOptions[position][3];
    }
    public String getAnswer(int position){
        if (position >= mQuestion.length)
            return "";
        return mAnswer[position];
    }
    public int getNoQuestions(){
        return mQuestion.length;
    }


}
