package com.quickrant.admin.web;

import java.sql.Timestamp;

import com.quickrant.api.models.Emotion;
import com.quickrant.api.models.Question;
import com.quickrant.api.utils.TimeUtils;

public class Helper {

	/* Fetch an emotion given an id */
	public static String getEmotion(String id) {
		if (id == null || id.isEmpty()) return null;
		Emotion emotion = (Emotion) Emotion.findById(Integer.parseInt(id));
		return emotion.getEmotion();
	}
	
	/* Fetch a question given an id */
	public static String getQuestion(String id) {
		if (id == null || id.isEmpty()) return null;
		Question question = (Question) Question.findById(Integer.parseInt(id));
		return question.getQuestion();
	}
	
	/* Fetch a question given an id */
	public static String getFormattedDate(Timestamp timestamp) {
		if (timestamp == null) return null;
		return TimeUtils.getFormattedDate(timestamp);
	}
	
	/* Fetch a question given an id */
	public static String getFormattedDateWithSec(Timestamp timestamp) {
		if (timestamp == null) return null;
		return TimeUtils.getFormattedDateWithSec(timestamp);
	}


}
