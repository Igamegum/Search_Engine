package Lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Administrator on 2017/2/19.
 */
public class AnalyzerStudy {

    private static String str = "极客学院,Lucene 案例开发";
    public  static  void print(Analyzer analyzer){
        StringReader stringReader = new StringReader((str));
        TokenStream tokenStream = analyzer.tokenStream("",stringReader);
        try {
            tokenStream.reset();
            CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
            System.out.println("分词技术" + analyzer.getClass()+" 分词版本:"+analyzer.getVersion());

            while(tokenStream.incrementToken()){
                System.out.print(term.toString() + "|");
            }

            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[]args)
    {
        Analyzer analyzer = null;
        Version matchVersion = Version.LUCENE_6_4_0;
        analyzer = new StandardAnalyzer();
        print(analyzer);
        analyzer = new CJKAnalyzer();
        print(analyzer);


    }
}
