package ipvc.estg.smartcity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val notesbutao = findViewById<Button>(R.id.notesBTN)

        notesbutao.setOnClickListener {
            val intent = Intent(this, Notes::class.java)
            startActivity(intent)
        }
    }
}

