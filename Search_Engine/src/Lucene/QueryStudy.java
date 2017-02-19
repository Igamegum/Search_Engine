package Lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Query;

/**
 * Created by Administrator on 2017/2/19.
 */
public class QueryStudy  {

    public static void main(String []args)
    {
        String key = "极客学院";
        String field = "name";
        String [] fields = {"name","content"};
        Analyzer analyzer = new StandardAnalyzer();
        Query query = null;


        QueryParser queryParser = new QueryParser(field,analyzer);
        try {
            query = queryParser.parse(key);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(QueryParser.class + query.toString());


        MultiFieldQueryParser parser1 = new MultiFieldQueryParser(fields,analyzer);
        try {
            query = parser1 .parse(key);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(MultiFieldQueryParser.class + query.toString());
    }
}
