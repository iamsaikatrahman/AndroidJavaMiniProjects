package com.saikat.trivia.data;

import com.saikat.trivia.model.Question;

import java.util.ArrayList;

public interface AnswerAsyncResponse {
    void processFinished(ArrayList<Question> questionArrayList);
}
