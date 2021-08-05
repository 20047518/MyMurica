package sg.edu.rp.c346.id20047518.mymurica;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
    RatingBar rb;
    Button btnCancel, btnUpdate, btnDelete;
    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_third );

        setTitle( getTitle().toString() + " ~ " + getResources().getText( R.string.title_activity_third ) );


        rb = (RatingBar) findViewById( R.id.rb );
        btnCancel = (Button) findViewById( R.id.btnCancel );
        btnDelete = (Button) findViewById( R.id.btnDelete );
        btnUpdate = (Button) findViewById( R.id.btnUpdate );
        etID = (EditText) findViewById( R.id.etID );
        etTitle = (EditText) findViewById( R.id.etTitle );
        etSingers = (EditText) findViewById( R.id.etSingers );
        etYear = (EditText) findViewById( R.id.etYear );

        Intent i = getIntent();
        final Island currentIsland = (Island) i.getSerializableExtra( "island" );

        etID.setText( currentIsland.getId() + "" );
        etTitle.setText( currentIsland.getName() );
        etSingers.setText( currentIsland.getDescription() );
        etYear.setText( currentIsland.getSize() + "" );
        rb.setRating( currentIsland.getStars() );
        /*switch (currentIsland.getStars()) {
            case 5:
                rb5.setChecked( true );
                break;
            case 4:
                rb4.setChecked( true );
                break;
            case 3:
                rb3.setChecked( true );
                break;
            case 2:
                rb2.setChecked( true );
                break;
            case 1:
                rb1.setChecked( true );
        }*/

        btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper( ThirdActivity.this );
                currentIsland.setName( etTitle.getText().toString().trim() );
                currentIsland.setDescription( etSingers.getText().toString().trim() );
                int year = 0;
                try {
                    year = Integer.valueOf( etYear.getText().toString().trim() );
                } catch (Exception e) {
                    Toast.makeText( ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT ).show();
                    return;
                }
                currentIsland.setSize( year );

           /*     int selectedRB = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById( selectedRB );
                currentIsland.setStars( Integer.parseInt( rb.getText().toString() ) );*/
                currentIsland.setStars( (int) rb.getRating() );
                int result = dbh.updateIsland( currentIsland );
                if (result > 0) {
                    Toast.makeText( ThirdActivity.this, "Island updated", Toast.LENGTH_SHORT ).show();
                    Intent i = new Intent();
                    setResult( RESULT_OK );
                    finish();
                } else {
                    Toast.makeText( ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT ).show();
                }
            }
        } );

        btnDelete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder( ThirdActivity.this );
                myBuilder.setTitle( "Danger" );
                myBuilder.setMessage( "Are you sure you want to delete the island" );
                myBuilder.setCancelable( false );
                myBuilder.setNegativeButton( "Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            DBHelper dbh = new DBHelper( ThirdActivity.this );
                            int result = dbh.deleteIsland( currentIsland.getId() );
                            if (result > 0) {
                                Toast.makeText( ThirdActivity.this, "Island deleted", Toast.LENGTH_SHORT ).show();
                                Intent i = new Intent();
                                setResult( RESULT_OK );
                                finish();
                            } else {
                                Toast.makeText( ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT ).show();
                            }

                    }
                } );
                myBuilder.setPositiveButton( "Cancel", null );
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        } );

/*
        btnDelete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper( ThirdActivity.this );
                int result = dbh.deleteSong( currentSong.getId() );
                if (result > 0) {
                    Toast.makeText( ThirdActivity.this, "Song deleted", Toast.LENGTH_SHORT ).show();
                    Intent i = new Intent();
                    setResult( RESULT_OK );
                    finish();
                } else {
                    Toast.makeText( ThirdActivity.this, "Delete failed", Toast.LENGTH_SHORT ).show();
                }
            }
        } );

 */

        btnCancel.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etID != etID) {

                    AlertDialog.Builder myBuilder = new AlertDialog.Builder( ThirdActivity.this );
                    myBuilder.setTitle( "Danger" );
                    myBuilder.setMessage( "Are you sure you want discard the changes" );
                    myBuilder.setCancelable( false );
                    myBuilder.setNegativeButton( "DISCARD", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();

                        }
                    } );
                    myBuilder.setPositiveButton( "DO NOT DISCARD", null );
                    AlertDialog myDialog = myBuilder.create();
                    myDialog.show();

                } else {
                    finish();
                }
                //finish();
            }
        } );

    }


}