package Lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2017/2/19.
 */
public class IndexSearch {

    public static void main(String[] args)
    {

        Directory directory = null;
        java.nio.file.Path _path = Paths.get("E:\\毕业设计\\Lucene_Index\\Search_Engine");
        try {
            directory = FSDirectory.open(_path);
            DirectoryReader directoryReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

            Analyzer analyzer = new StandardAnalyzer();

            QueryParser parser = new QueryParser("content",analyzer);
            Query query = parser.parse("Lucene");
            TopDocs topDocs = indexSearcher.search(query,10);
            if(topDocs != null)
            {
                System.out.println("符合条件的文档总数为"+topDocs.totalHits);
                for(int i=0;i<topDocs.totalHits;i++)
                {
                    Document doc = indexSearcher.doc(topDocs.scoreDocs[i].doc);
                    System.out.println("id = "+doc.get("id") );
                    System.out.println("id = "+doc.get("content") );
                    System.out.println("id = "+doc.get("num") );
                    System.out.println("id = "+doc.get("nusm") );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
