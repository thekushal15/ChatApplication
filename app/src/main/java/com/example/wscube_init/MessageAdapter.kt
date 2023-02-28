package com.example.wscube_init

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth

//import com.google.firebase.database.core.Context

class MessageAdapter(val context: Context, val messageList: ArrayList<Message>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECIEVE = 1
    val ITEM_SENT = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (viewType ==1){
            //inflate recieve
            val view: View = LayoutInflater.from(context).inflate(R.layout.recieve, parent, false)
            return RecieveViewHolder(view)
        }
        else{
            //inflate sent
            val view: View = LayoutInflater.from(context).inflate(R.layout.sent, parent, false)
            return SentViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {

        val currentMesssage = messageList[position]
        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMesssage.senderId)){
            return ITEM_SENT
        }else{
            return ITEM_RECIEVE
        }

    }

    override fun getItemCount(): Int {
            return messageList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMessage = messageList[position]
        if (holder.javaClass == SentViewHolder::class.java){
            //do the stuff for sent view holder


            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message


        }else{
            // do the stuff for recieve view holder
            val viewHolder = holder as RecieveViewHolder
            holder.recieveMessage.text = currentMessage.message
        }
    }
    class SentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val sentMessage =  itemView.findViewById<TextView>(R.id.txt_sent_message)
    }
    class RecieveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val recieveMessage =  itemView.findViewById<TextView>(R.id.txt_recieve_message)

    }

}