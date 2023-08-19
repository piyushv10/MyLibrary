package com.example.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import static com.example.mylibrary.BookActivity.BOOK_ID_KEY;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {


    public static final String BOOK_ID_KEY = "bookId";
    Book b1;
    private TextView nameText,txtBookName,authorText;
    private TextView pagesText,descriptionText,txtDescription;
    private TextView txtPages,txtAuthorName;
    private Button btnAddToCurrentlyReading,btnAddToAlreadyRead,btnViewPdf;
    private Button btnAddToWishList,btnAddToFavourites,btnBuyBook;
    private ImageView bookImage;
    private ArrayList<Book> books = new ArrayList<Book>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();

        //TODO: get the data from recycler view in here

        btnViewPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),PdfViewerActivity.class);
//                intent.putExtra(BOOK_ID_KEY,b1.getId());
                startActivity(intent);
            }
        });

        btnBuyBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AmazonActivity.class);
                btnBuyBook.setEnabled(false);
                intent.putExtra("url","https://www.amazon.in/Atomic-Habits-James-Clear/dp/1847941834");
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if (null != intent){
            int bookId = intent.getIntExtra(BOOK_ID_KEY,-1);
            if(bookId != -1){
                Book incomingBook = new Util(this).getBookById(bookId);
                if (null != incomingBook){
                    setData(incomingBook);
                    handleAlreadyRead(incomingBook);
                    handleWishListBooks(incomingBook);
                    handleCurrentlyReadingBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);
                }
            }
        }
    }

    private void handleFavouriteBooks(final Book book) {
        ArrayList<Book> favouriteBooks = new Util(this).getFavouriteBooks();

        boolean existInFavouriteBooks = false;

        for (Book b: favouriteBooks) {
            if(b.getId() == book.getId()){
                existInFavouriteBooks = true;
            }
        }
        if(existInFavouriteBooks){
            btnAddToFavourites.setEnabled(false);
        }else{
            btnAddToFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Util.getObject(BookActivity.this).addToFavouriteBooks(book)){
                        Toast.makeText(BookActivity.this, "BOOK ADDED", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), FavouriteBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "TRY AGAIN", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void handleCurrentlyReadingBooks(final Book book) {
        ArrayList<Book> currentlyReadingBooks = new Util(this).getCurrentlyReadingBooks();

        boolean existInCurrentlyReadingBooks = false;

        for (Book b: currentlyReadingBooks) {
            if(b.getId() == book.getId()){
                existInCurrentlyReadingBooks = true;
            }
        }
        if(existInCurrentlyReadingBooks){
            btnAddToCurrentlyReading.setEnabled(false);
        }else{
            btnAddToCurrentlyReading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (new Util(BookActivity.this).addToCurrentlyReadingBooks(book)){
                        Toast.makeText(BookActivity.this, "BOOK ADDED", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), CurrentlyReadingBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "TRY AGAIN", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void handleWishListBooks(final Book book) {
        ArrayList<Book> wishListBooks = new Util(this).getWishListBooks();

        boolean existInWishListBooks = false;

        for (Book b: wishListBooks) {
            if(b.getId() == book.getId()){
                existInWishListBooks = true;
            }
        }
        if(existInWishListBooks){
            btnAddToWishList.setEnabled(false);
        }else{
            btnAddToWishList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (new Util(BookActivity.this).addToWishListBooks(book)){
                        Toast.makeText(BookActivity.this, "BOOK ADDED", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), WishListBooksActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "TRY AGAIN", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    /**
     * Enable and Disable button
     * add the book to already read book Arraylist
     * @param book
     */

    private void handleAlreadyRead(Book book) {
        ArrayList<Book> alreadyReadBooks = new Util(this).getAlreadyReadBooks();
        boolean existInAlreadyReadBooks = false;

        for (Book b: alreadyReadBooks) {
            if(b.getId() == book.getId()){
                existInAlreadyReadBooks = true;
            }
        }
        if(existInAlreadyReadBooks){
            btnAddToAlreadyRead.setEnabled(false);
        }else{
            btnAddToAlreadyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (new Util(BookActivity.this).addToAlreadyRead(book)){
                        Toast.makeText(BookActivity.this, "BOOK ADDED", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(BookActivity.this, "TRY AGAIN", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
    }

    private void setData(Book book) {
       txtBookName.setText(book.getName());
        txtAuthorName.setText(book.getAuthor());
        txtPages.setText(String.valueOf(book.getPages()));
        txtDescription.setText(book.getLongDesc());
        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);
    }

    private void initViews() {
        btnViewPdf = findViewById(R.id.viewPdf);
        btnAddToCurrentlyReading = findViewById(R.id.btnAddToCurrentlyReading);
        btnAddToAlreadyRead = findViewById(R.id.btnAddToAlreadyRead);
        btnAddToWishList = findViewById(R.id.btnAddToWishList);
        btnAddToFavourites = findViewById(R.id.btnAddToFavourites);
        btnBuyBook = findViewById(R.id.btnBuyBook);
        bookImage = findViewById(R.id.bookImage);

        txtBookName = findViewById(R.id.txtBookName);
        txtPages = findViewById(R.id.txtPages);
        txtDescription = findViewById(R.id.txtDescription);
        txtAuthorName = findViewById(R.id.txtAuthorName);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}