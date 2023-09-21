package com.example.bharatyatrisathi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewholder>{

    List <Message> messageList;

    public MessageAdapter(List<Message> messageList) {

        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View chatView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, null);

        MyViewholder myViewholder = new MyViewholder(chatView);
        return myViewholder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        Message message = messageList.get(position);
        if(message.getSentBy().equals(Message.SENT_BY_ME)){
            holder.leftChatView.setVisibility(View.GONE);
            holder.rightChatView.setVisibility(View.VISIBLE);
            holder.rightTextView.setText(message.getMessage());
        }else{
            holder.rightChatView.setVisibility(View.GONE);
            holder.leftChatView.setVisibility(View.VISIBLE);
            holder.leftTextView.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder{

        LinearLayout leftChatView , rightChatView;
        TextView leftTextView, rightTextView;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);

            leftChatView = itemView.findViewById(R.id.leftChatView);
            rightChatView = itemView.findViewById(R.id.rightChatView);
            leftTextView = itemView.findViewById(R.id.leftChatTextView);
            rightTextView = itemView.findViewById(R.id.RightChatTextView);


        }
    }
}

