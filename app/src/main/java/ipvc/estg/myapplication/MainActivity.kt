package ipvc.estg.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ipvc.estg.myapplication.api.EndPoints
import ipvc.estg.myapplication.api.OutputPost
import ipvc.estg.myapplication.api.ServiceBuilder
import ipvc.estg.myapplication.api.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userText = findViewById<EditText>(R.id.usernametxt)
        val pwText = findViewById<EditText>(R.id.passwordtxt)

        val notesbutao = findViewById<Button>(R.id.notesBTN)

        val loginbtn = findViewById<Button>(R.id.loginBTN)

        notesbutao.setOnClickListener {
            val intent = Intent(this, NotesActivity::class.java)
            startActivity(intent)
        }

        loginbtn.setOnClickListener {

            if(userText.text.isEmpty() || pwText.text.isEmpty()) {

                Toast.makeText(this@MainActivity,R.string.AddNoteError, Toast.LENGTH_SHORT).show()

            } else {

                val request = ServiceBuilder.buildService(EndPoints::class.java)
                val call = request.login(userText.text.toString(), pwText.text.toString())

                call.enqueue(object : Callback<OutputPost> {
                    override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {
                        if (response.isSuccessful){
                            if(response.body()?.status!!) {
                                Toast.makeText(this@MainActivity, getString(R.string.welcome) + response.body()?.username, Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@MainActivity, LoginLandingPage::class.java)
                                intent.putExtra("id", response.body()!!.id)
                                intent.putExtra("Username", response.body()!!.username)
                                startActivity(intent)
                                finish()
                            } else {

                                Toast.makeText(this@MainActivity, R.string.loginError, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                    }

                })

            }


        }
    }


}