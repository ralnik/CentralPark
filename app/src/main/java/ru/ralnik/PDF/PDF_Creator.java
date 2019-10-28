package ru.ralnik.PDF;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.Line;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import android.widget.Toast;


import ru.ralnik.centralpark.R;
import ru.ralnik.model.Flat;
import ru.ralnik.sqlitedb.FlatRepository;

public class PDF_Creator {
    private final String filename;
    private final Context context;
    private final List<Flat> listFlat;

    public PDF_Creator(Context context, String filename, List<Flat> listFlats) {
        this.context = context;
        this.filename = filename;
        this.listFlat = listFlats;
    }

    public PDF_Creator(Context context, String filename) {
        this.context = context;
        this.filename = filename;
        this.listFlat = new FlatRepository(context).getFavoriteFlats();
    }

    public void create(){

        File dir = new File(new File(filename).getAbsolutePath());
        File file = new File(filename);

        if(!dir.exists())
            dir.mkdirs();

        //delete file if exitst
        if(file.exists()){
            file.delete();
        }

        // create a new document
        Document document = new Document(PageSize.A4);
        BaseFont baseFont = loadBaseFont("/assets/fonts/arial.ttf");
        Font font = new Font(baseFont, 16, Font.NORMAL, BaseColor.BLACK);

        try {

            FileOutputStream fOut = new FileOutputStream(file);

            PdfWriter.getInstance(document, fOut);

            //open the document
            document.open();


            for (Flat flat : listFlat) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.plan_flat100_floor6_corpus1);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                Image myImg = Image.getInstance(stream.toByteArray());
                myImg.setDpi(50, 50);
                myImg.setAlignment(Image.MIDDLE);
                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //add image to document
                document.add(myImg);

                Paragraph p1 = new Paragraph(flat.getNameCountRoomsFlat());

                p1.setAlignment(Paragraph.ALIGN_CENTER);
                p1.setFont(font);
                document.add(p1);


                document.add(new Paragraph("COST : " + Flat.makePrettyCost(flat.getPrice().toString()) + " руб.",font));
                document.add(new Paragraph("Корпус: " + flat.getCorpus(),font));
                document.add(new Paragraph("Номер квартиры: " + flat.getNom_kv(),font));
                document.add(new Paragraph("Этаж: " + flat.getEtag(),font));
                document.add(new Paragraph("Площадь: " + flat.getPloshad(),font));

                document.newPage();

            }

        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } finally
        {
            document.close();
        }

    }

    protected Bitmap getBitmap(Drawable drawable) {
        return (drawable != null) ? ((BitmapDrawable) drawable).getBitmap() : null;
    }

    private static BaseFont loadBaseFont(String fontName) {
        BaseFont baseFont= null;
        try {
            baseFont = BaseFont.createFont(fontName, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baseFont;
    }
}
