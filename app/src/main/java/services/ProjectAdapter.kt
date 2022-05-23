package services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.scrivenermobile.R
import com.example.scrivenermobile.SecondFragmentDirections
import java.io.File

class ProjectAdapter(private val dataSet: Array<ScrivenerFolder>) :
    RecyclerView.Adapter<ProjectAdapter.ViewHolder>() {
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val folderView: TextView
            val fileView: TextView


            init {
                // Define click listener for the ViewHolder's View.
                folderView = view.findViewById(R.id.folderView)
                fileView = view.findViewById(R.id.fileView)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.text_row_item, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.folderView.text = dataSet[position].folderName + " (Mappe)"
            viewHolder.fileView.text = "test" //dataSet[position].files[0].name

            viewHolder.folderView.setOnClickListener(View.OnClickListener {
                val path = dataSet[position].folderName + "/" + viewHolder.fileView.text.toString()
                val action = SecondFragmentDirections.actionSecondFragmentToThirdFragment(path)
                it.findNavController().navigate(action)
            })
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size
    }
