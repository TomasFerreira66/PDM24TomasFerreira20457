package com.example.armazenamento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.*
import androidx.room.Room
import com.example.armazenamento.ui.theme.ArmazenamentoTheme
import kotlinx.coroutines.flow.Flow

// Entity for the database
@Entity(tableName = "example_table")
data class ExampleEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    val description: String
)

// DAO interface
@Dao
interface ExampleDao {
    @Insert
    suspend fun insert(example: ExampleEntity)

    @Query("SELECT * FROM example_table WHERE id = :id")
    fun getById(id: Int): Flow<ExampleEntity>

    @Delete
    suspend fun delete(example: ExampleEntity)
}

// Room database
@Database(entities = [ExampleEntity::class], version = 1)
abstract class ExampleDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao

    companion object {
        @Volatile private var INSTANCE: ExampleDatabase? = null

        fun getInstance(context: android.content.Context): ExampleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExampleDatabase::class.java,
                    "example_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // enableEdgeToEdge()

        // Get the database instance
        val db = ExampleDatabase.getInstance(applicationContext)
        val exampleDao = db.exampleDao()

        setContent {
            ArmazenamentoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArmazenamentoTheme {
        Greeting("Android")
    }
}
