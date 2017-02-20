package Lucene;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2017/2/19.
 */
public class IndexCreate {

     public static void main(String []args)throws IOException,ParseException
     {
         Analyzer analyzer = new StandardAnalyzer();
         IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
         indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

         Directory directory = null;
         IndexWriter indexWriter = null;

         java.nio.file.Path _path = Paths.get("E:\\毕业设计\\Lucene_Index\\Search_Engine");
         directory = FSDirectory.open(_path);
         //解锁的判断
         /*
         if(indexWriter.isLocked(directory)){
             indexWriter.unlock(directory);
         }*/

         indexWriter = new IndexWriter(directory,indexWriterConfig);


         Document doc1 = new Document() ;
         doc1.add(new StringField("id","abcde", Field.Store.YES));
         doc1.add(new TextField("content","极客学院", Field.Store.YES));
         //doc1.add(new Field("num", 1, TextField.TYPE_STORED));
         doc1.add(new Field("num", "1", TextField.TYPE_STORED));


         indexWriter.addDocument(doc1);



         Document doc2 = new Document() ;
         doc2.add(new StringField("id","asdff", Field.Store.YES));
         doc2.add(new TextField("content","Lucene案例开发", Field.Store.YES));

         doc2.add(new Field("num", "2", TextField.TYPE_STORED));


         indexWriter.addDocument(doc2);

         indexWriter.commit();

         indexWriter.close();


     }
}
