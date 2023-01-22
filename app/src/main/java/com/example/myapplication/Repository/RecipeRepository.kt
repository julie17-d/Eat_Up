import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.`object`.Fetch
import com.example.myapplication.models.HealthModel
import com.example.myapplication.models.RecipeX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeRepository {
    fun getRecipes(query: String): LiveData<List<RecipeX>> {
        val fetch = Fetch.service
        val call = fetch.getHealthModel(query, "bf609ed4", "12b3eea3417cea7f8b96953e19937f56", "public")
        val recipeList = MutableLiveData<List<RecipeX>>()

        call.enqueue(object : Callback<HealthModel> {
            override fun onResponse(call: Call<HealthModel>, response: Response<HealthModel>) {
                if (response.isSuccessful) {
                    val recipes = response.body()!!.hits.map { it.recipe }
                    recipeList.value = recipes
                }
            }

            override fun onFailure(call: Call<HealthModel>, t: Throwable) {
                Log.e("fail", t.message.toString())
            }
        })

        return recipeList
    }
}
