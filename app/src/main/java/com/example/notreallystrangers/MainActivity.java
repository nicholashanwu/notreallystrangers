package com.example.notreallystrangers;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notreallystrangers.model.Question;
import com.example.notreallystrangers.repository.AppDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class MainActivity extends AppCompatActivity {

	private FloatingActionButton mButton;
	private SwipeStack swipeStack;

	private	List<Question> questionList = new ArrayList<>();
	private	List<Question> shuffledQuestionList = new ArrayList<>();


	private TextView exampleTextView;

	private boolean shuffled = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		swipeStack = (SwipeStack) findViewById(R.id.bodyTextView);
//		exampleTextView = (TextView) findViewById(R.id.exampleTextView);

		swipeStack.setListener(new SwipeStack.SwipeStackListener() {
			@Override
			public void onViewSwipedToLeft(int position) {
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);

			}

			@Override
			public void onViewSwipedToRight(int position) {
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
			}

			@Override
			public void onStackEmpty() {
				swipeStack.performHapticFeedback(HapticFeedbackConstants.CONFIRM);
			}
		});




		shuffledQuestionList = AppDatabase.getInstance(this).questionDao().getAllQuestions();
		questionList = AppDatabase.getInstance(this).questionDao().getAllQuestions();

		Collections.shuffle(shuffledQuestionList);

//		questionStack.addAll(questionList);
//		shuffledQuestionStack.addAll(shuffledQuestionList);

//		exampleTextView.setText(questionList.get(0).getQuestionBody());
		System.out.println(shuffledQuestionList.get(0));
		System.out.println(questionList.get(0));






		mButton = (FloatingActionButton) findViewById(R.id.button);

		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				OptionsBottomSheet bottomSheet = new OptionsBottomSheet();
				Bundle bundle = new Bundle();
				bundle.putBoolean("shuffled", shuffled);
				bottomSheet.setArguments(bundle);

				bottomSheet.show(getSupportFragmentManager(), "bottomSheet");

			}
		});



		if (savedInstanceState != null) {
			shuffled = savedInstanceState.getBoolean("shuffled");
			questionList = savedInstanceState.getParcelableArrayList("questionList");
			shuffledQuestionList = savedInstanceState.getParcelableArrayList("shuffledQuestionList");
		}

		if(shuffled) {
			swipeStack.setAdapter(new SwipeStackAdapter(shuffledQuestionList, this));
		} else {
			swipeStack.setAdapter(new SwipeStackAdapter(questionList, this));
		}

		System.out.println("shuffled is " + shuffled);
	}
	//base
	//honest dating
	//inner circle
	//relationship
	//breakup
	//own it

	public void sortList() {
		swipeStack.resetStack();
		swipeStack.setAdapter(new SwipeStackAdapter(questionList, this));
		shuffled = false;
	}

	public void shuffleList() {
		swipeStack.resetStack();
		swipeStack.setAdapter(new SwipeStackAdapter(shuffledQuestionList, this));
		shuffled = true;
	}

	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);


		System.out.println("IS THIS BEING SAVED");
		outState.putBoolean("shuffled", shuffled);

		outState.putParcelableArrayList("questionList", (ArrayList<? extends Parcelable>) questionList);
		outState.putParcelableArrayList("shuffledQuestionList", (ArrayList<? extends Parcelable>) shuffledQuestionList);

	}

	private void prepopulateData() {

		AppDatabase db = AppDatabase.getInstance(this);



		//Base pack
		db.questionDao().insertQuestion(new Question("base", "false", "Do you think I've ever been in love?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What about me is most strange or unfamiliar?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Do you think I've ever had my heart broken?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you think I'd splurge on?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you think my major is?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you think I'm going to do in the future (work/employment)"));
		db.questionDao().insertQuestion(new Question("base", "false", "What was your first impression of me?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Do you think I was popular in high school?"));
		db.questionDao().insertQuestion(new Question("base", "false", "On a scale of 1 - 10, how messy do you think my car is?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Do you think I like hot cheetos?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Do you think I like to read?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Do you think I'm in Greek Life? (frats/sororities)"));
		db.questionDao().insertQuestion(new Question("base", "false", "Do you think I have a sibling? Older or younger?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Who do you think is my favorite artist?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Where do you think I grew up?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Do you think I like Taco Bell? "));
		db.questionDao().insertQuestion(new Question("base", "false", "What's the last thing you lied about?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What is your most defining characteristic?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Are you lying to yourself about anything?"));
		db.questionDao().insertQuestion(new Question("base", "false", "How are you, really?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What is your least favorite personality trait in a person?"));
		db.questionDao().insertQuestion(new Question("base", "false", "When is the last time you felt lucky to be you?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Which one of your parent's personality traits do you want to keep/let go of?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What would you tell your younger self and what have you learned from this lesson?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What is the last thing you lied to your mum about?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What's the worst pain you've ever been in that wasn't physical?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What's your father's name and one thing about him?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What's the most unexplainable thing that has ever happened to you?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What would your younger self not believe about your life today?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What non-domestic animal describes you and why?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What is your favorite adjective with which to be described?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What are you still trying to prove to yourself?"));
		db.questionDao().insertQuestion(new Question("base", "false", "When is the last time you cried?"));
		db.questionDao().insertQuestion(new Question("base", "false", "If you could instill one personality trait in your child, what would it be?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What's your favorite song lyric that you can think of off the top of your head?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Think of someone that you admire. What made you think about them specifically?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What's the biggest mistake you've made?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What's a phrase you say that you wish you would stop saying?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What are you most excited for today? Big or small."));
		db.questionDao().insertQuestion(new Question("base", "false", "Which movie do you wish you could watch again for the first time and why?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Based on what you learned about me what would you recommend I read?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What about me surprised you?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Based on what you know about me, do you have a Netflix/movie recommendation?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you think my superpower is?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you think our most important similarities are?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you think is one thing I could do that would drastically improve my life?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What would be the perfect gift for me?"));
		db.questionDao().insertQuestion(new Question("base", "false", "How would you describe me to a stranger?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do I need to hear right now?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Based on what you've learned about me, does my social media portray me accurately?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What is a lesson you will take away from our conversation?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What can I help you with?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you think do I fear the most?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What am I most qualified to give advice about?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What about me is the hardest for you to understand?"));
		db.questionDao().insertQuestion(new Question("base", "false", "If we were a band what would be our name?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What parts of yourself do you see in me?"));
		db.questionDao().insertQuestion(new Question("base", "false", "How does one earn your vulnerability?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you recommend I should let go of?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What has this conversation taught you about yourself?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you think my defining characteristic is?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What question were you most afraid to answer?"));
		db.questionDao().insertQuestion(new Question("base", "false", "Why do you think we met?"));
		db.questionDao().insertQuestion(new Question("base", "false", "When this game is over, what will be something you will remember about me?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you think my weakness is?"));
		db.questionDao().insertQuestion(new Question("base", "false", "How do our personalities complement each other?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you think I should know about myself that perhaps I'm unaware of?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What would make you feel closer to me?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What do you admire most about me?"));
		db.questionDao().insertQuestion(new Question("base", "false", "In one word, describe how you feel right now."));
		db.questionDao().insertQuestion(new Question("base", "false", "Do you believe everyone has a calling? If so, do you think I've found mine?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What answer of mine made you light up?"));
		db.questionDao().insertQuestion(new Question("base", "false", "What's the most attractive quality about me that isn't physical?"));


		// Base pack wildcards
		db.questionDao().insertQuestion(new Question("base", "true", "Based on what you learned re-write each other's dating app bios."));
		db.questionDao().insertQuestion(new Question("base", "true", "Draw a picture together (30 secs)"));
		db.questionDao().insertQuestion(new Question("base", "true", "Look into each other's eyes for 30 seconds. What did you notice?"));
		db.questionDao().insertQuestion(new Question("base", "true", "Write a message to the other person and give it to them. Open it once you have left."));
		db.questionDao().insertQuestion(new Question("base", "true", "Think of your favourite childhood TV show. Say it out loud together on the count of three. "));
		db.questionDao().insertQuestion(new Question("base", "true", "Take a selfie together"));
		db.questionDao().insertQuestion(new Question("base", "true", "Staring contest! First person to blink reveals a personal problem and asks the other person for advice. "));
		db.questionDao().insertQuestion(new Question("base", "true", "Create your own question and ask the other person."));
		db.questionDao().insertQuestion(new Question("base", "true", "Tell the other person to close their eyes. After 10 seconds, tell the other person your favourite memory of them or the two of you"));
		db.questionDao().insertQuestion(new Question("base", "true", "Tell the other person to close their eyes and keep them closed. After 15 seconds, kiss them."));
		db.questionDao().insertQuestion(new Question("base", "true", "Press shuffle on your music library. Explain the first song that comes up."));
		db.questionDao().insertQuestion(new Question("base", "true", "Both players share something they are grateful for in this current moment."));
		db.questionDao().insertQuestion(new Question("base", "true", "Both players draw their feelings. Compare with the other person."));
		db.questionDao().insertQuestion(new Question("base", "true", "Show the first photo in your camera roll. Explain."));
		db.questionDao().insertQuestion(new Question("base", "true", "Both players think of something that they dislike that most people don't. On the count of three, say it out loud."));
		db.questionDao().insertQuestion(new Question("base", "true", "Share a favourite memory of yours. Compare."));
		db.questionDao().insertQuestion(new Question("base", "true", "Both players sing their favourite song lyrics."));
		db.questionDao().insertQuestion(new Question("base", "true", "Share your phone's screen time."));
		db.questionDao().insertQuestion(new Question("base", "true", "Write down your top 2 relationship dealbreakers. Both players. Compare."));
		db.questionDao().insertQuestion(new Question("base", "true", "Staring contest! First person to blink has to show an embarrassing video from at least 3 years ago."));





		// Honest Dating Expansion
		db.questionDao().insertQuestion(new Question("honest dating", "false", "How many relationships do you think I've been in? Explain."));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "Just by looking at me, what would you think I do for a living?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What was the first thing you noticed about me when we met in person?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What do you think my ideal type is? Describe in detail."));
		db.questionDao().insertQuestion(new Question("honest dating", "true", "Write down the top 3 most important qualities in a romantic partner. Both players. Compare."));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What do you think I was like in high school?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What do you think my favourite drink is?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "Do you think I'm on good terms with my exes? Why or why not?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "When's the last time you ghosted someone and why?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What about my intrigued you besides my physical appearance?"));
		db.questionDao().insertQuestion(new Question("honest dating", "true", "Share something you typically wouldn't on a first date. Both players."));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What do your friends know about me, if anything?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What part of your job energizes you the most? Leaves you the most drained?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "How has your view of love evolved over time, if at all?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "Describe your worst heartbreak. What did it teach you?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What are you feeling a lot of lately?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "Do you have any unconventional opinions when it comes to romantic relationships?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "I know I really like someone when ______"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "Has sex ever been a dealbreaker in past relationships? Explain."));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "How do you define 'dating'?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "Have you ever considered an open relationship? Why or why not?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "Who in your life do you feel most yourself around? Why?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What is something new you've learned about yourself recently?"));
		db.questionDao().insertQuestion(new Question("honest dating", "true", "Give each other tattoos with the nearest pen or marker. 30 seconds."));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What's the hardest part about dating you?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What are you overthinking right now?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What have you tolerated from others in the past that you no longer have space for?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What dating advice would you give your younger self?"));
		db.questionDao().insertQuestion(new Question("honest dating", "true", "Look through each other's social media. Pick your favourite photo. Compare and explain."));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "How emotionally available do you currently feel? Explain."));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What has being single taught you about yourself?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What was the worst date you've ever been on and why?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What about you has felt repeatedly misunderstood by others, if anything?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What has being single taught you about yourself?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What's a non-negotiable in your life?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What did the people who raised you teach you about love? How has that shaped your views on love today?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What song should I listen to on my way home?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "Based on what you've learned about me, what do you think I'm looking for romantically?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What mannerisms of mine stood out the most to you, if any?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "On a scale of 1-10, how emotionally available do I seem? Explain."));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "How would you describe our date in one word?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "If you were to write my dating profile bio, what would it say?"));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What part of my personality isn't shown online?"));
		db.questionDao().insertQuestion(new Question("honest dating", "true", "Write your partner a text outside of your comfort zone at the end of this date. Both players."));
		db.questionDao().insertQuestion(new Question("honest dating", "false", "What do you find most attractive about me that isn't physical?"));
		db.questionDao().insertQuestion(new Question("honest dating", "true", "Ask literally anything. Permission to go there."));

		// Inner Circle Expansion
		db.questionDao().insertQuestion(new Question("inner circle", "false", "What do you think I'm most sensitive to?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "What do you think my main love language is?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "What's the hardest truth you had to face this past year?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "What's brought you the most unexpected joy recently?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "Who in your life do you feel you can be most vulnerable with? Explain."));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "Have I ever challenged your worldview? Explain."));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "What is a moment in our relationship that you felt undeniably loved?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "How can I add 1% more happiness to your life?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "A perfect day together would be ______"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "What's the most fun you can remember having with me recently?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "How can I best support you in this chapter in your life?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "What's a feeling you're uncomfortable sharing with me?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "What are you currently working through that I don't see?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "When's the last time you felt lucky to be you?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "When's the last time I surprised you?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "When was the last time you felt most yourself?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "What are you proudest of me for?"));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "What's something we should celebrate together?"));
		db.questionDao().insertQuestion(new Question("inner circle", "true", "Draw your favourite memory with me. Both players. Compare."));
		db.questionDao().insertQuestion(new Question("inner circle", "true", "Set an intention for the coming week. Stick to it. Both players."));
		db.questionDao().insertQuestion(new Question("inner circle", "true", "Finish the sentence: When I'm hurt, I ______. Both players."));
		db.questionDao().insertQuestion(new Question("inner circle", "false", "Finish the sentence: Thank you for ______. Both players."));
		db.questionDao().insertQuestion(new Question("inner circle", "true", "Ask me something you think is off limits. Both players."));
		db.questionDao().insertQuestion(new Question("inner circle", "true", "You've shown me ______ about myself."));

		// Relationship Expansion
		db.questionDao().insertQuestion(new Question("relationship", "false", "How important do you think birthdays and holidays are to me? Explain."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "Who do you think was more nervous on our first date? Explain."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What was the most thing you noticed about me?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What assumption did you make about me that turned out to be false?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "On a scale of 1-10, how open do you feel I am with you? Explain."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What would a day of completely spoiling me look like?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What do you think my perfect date night would be?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "The hardest thing for me to reveal about myself to you was ______"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "How does one earn your trust? Have I earned it? How can I earn more?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What did our worst argument teach you?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What has this relationship taught you about yourself?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What's the most romantic thing I've done for you recently?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What's one small way I can be a better partner?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What's the most attractive thing I do without realizing it?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What are you currently working through that I don't see, if anything?"));
		db.questionDao().insertQuestion(new Question("relationship", "true", "Write down one small way you can be better in this relationship. Both players. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What song best describes our relationship?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "Do you think my job affects me positively or negatively? How does it affect us?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What have you been extra sensitive to lately?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What's the best gift I've given you? Material and immaterial?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What feelings are hard for you to communicate to me? How can I make it easier?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What about you feels hardest to love?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What immediately attracted you to me? What attracted you more over time?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What do you wish we did more of?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What made you fall in love with me?"));
		db.questionDao().insertQuestion(new Question("relationship", "true", "On a scale of 1-10 write down the importance of having kids to you. Both players. Compare and explain. "));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What's the most important lesson a past relationship has taught you?"));
		db.questionDao().insertQuestion(new Question("relationship", "true", "Write down the top 3 things your partner does that makes you feel most loved. Both players. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "Finish the sentence: Thank you for accepting ______"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "Are there any insecurities from previous relationships that you carried into this one? If so, what are they?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "How do our strengths and weaknesses complement each other?"));
		db.questionDao().insertQuestion(new Question("relationship", "true", "Write a short guide on how to love your partner well. 30 seconds. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What have I helped you appreciate about yourself?"));
		db.questionDao().insertQuestion(new Question("relationship", "true", "Write a love song for your partner. 1 minute. Perform for each other."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What recent experience made you feel closer to me?"));
		db.questionDao().insertQuestion(new Question("relationship", "true", "Draw one of your favourite memories from our relationship. Both players. 30 seconds. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "How do I show that I love you without telling you?"));
		db.questionDao().insertQuestion(new Question("relationship", "true", "Write down the title you would give this chapter of our relationship. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "When was the last time I hurt you, perhaps unintentionally?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What fear do you think holds me back the most?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "How have you seen me grow in this relationship?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What's one small way we can improve our sex life?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "Have I helped you change your mind about anything?"));
		db.questionDao().insertQuestion(new Question("relationship", "true", "Ask your partner something you've never asked them before. Both players."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "How does our age difference, or lack thereof, affect us, if at all?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What answer of mine surprised you the most throughout this game, if any?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What don't I give myself credit for?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "Is there anything you still don't know about me that you wish you did?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What about our relationship are you proudest of?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What did this conversation teach you about our relationship? What did it teach you about yourself?"));
		db.questionDao().insertQuestion(new Question("relationship", "true", "Write down the top 3 things you're most grateful for in your partner. 30 seconds. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", "false", "What goal would feel best for you to accomplish this year? How can I support you in that?"));
		db.questionDao().insertQuestion(new Question("relationship", "false", "Thank you for helping me realize ______ about myself."));
		db.questionDao().insertQuestion(new Question("relationship", "true", "Write down your intention for our relationship. Compare."));

		//Breakup Expansion
		db.questionDao().insertQuestion(new Question("breakup", "false", "What has helped you heal from a heartbreak in the past, besides time and meeting someone new"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "After your most recent breakup, what song did you play on repeat?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Picture you're talking to your ex. Finish the sentence: You hurt me, but thank you for ______"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What have all your previous relationships had in common that you'd like to avoid moving forward, if anything?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Did their love leave you feeling good most of the time? If not, what was the dominant feeling they left you with?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Were you lying to yourself about anything during your last relationship? If so, what was it?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What did your last relationship make you appreciate about yourself?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What are 3 things you can appreciate about being single?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Are you talking to other people currently? If so, are you seeking a real connection or needing a distraction?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What part of your last relationship worked? What part of it hurt?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Do you want closure or just an excuse to talk to them? Explain."));
		db.questionDao().insertQuestion(new Question("breakup", "false", "How can you show up for yourself when no one else can?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "How has your last relationship prepared for you for your next one?"));
		db.questionDao().insertQuestion(new Question("breakup", "true", "Write down 3 things you love about yourself that aren't physical."));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What parts of yourself do you need to break up with?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What would feel best to fully accept in this current moment?"));
		db.questionDao().insertQuestion(new Question("breakup", "true", "Write down 3 things you wouldn't change about this current moment."));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Who in your life has been treating you right lately? Let them know before moving on to the next card."));
		db.questionDao().insertQuestion(new Question("breakup", "false", "How did you get over your first love?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Were you more attracted to their potential or who they really were? Explain."));
		db.questionDao().insertQuestion(new Question("breakup", "true", "Write a note to your future self. Place it somewhere safe. Set a reminder to open it in 3 months."));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What title would you give this chapter of your healing process?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Would you encourage your future child to be in a relationship like your last? Why or why not?"));
		db.questionDao().insertQuestion(new Question("breakup", "true", "Draw how your heart feels today."));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What story about love have you been telling yourself that no longer serves you? How would you like to change that story?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What can you be doing now with all the energy you were putting into them?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What's the best lesson an ex has ever taught you?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What were you over-rationalizing throughout your last relationship, if anything?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Do you have a limiting belief about love? Where do you think it stems from? How can you challenge this belief?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What can you do for yourself today, even if you don't enjoy it, to make future you happy?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Did your last relationship reveal some toxic traits in yourself that you'd like to work on? If so, what are they?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What is your heart telling you? What is your mind telling you?"));
		db.questionDao().insertQuestion(new Question("breakup", "true", "Write a stand up routine about your last relationship. 1 minute. Read it out loud."));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What do you want to receive from a future relationship? What do you want to give? Get specific."));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Were there any patterns in your previous relationship that you're newly aware of? If so, what were they?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Significant other aside, when you visualize coming home to a life you love, what does that look like? Get specific."));
		db.questionDao().insertQuestion(new Question("breakup", "true", "Write down 3 things about your last relationship that you will no longer accept moving forward. Get specific. "));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What was the biggest turning point in your dating life?"));
		db.questionDao().insertQuestion(new Question("breakup", "true", "Write down 3 things you love about yourself physically. Permission to brag."));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What validation are you seeking from a partner? How can you give it to yourself?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Imagine you're talking to your ex. Finish the sentence: I'm sorry for ______"));
		db.questionDao().insertQuestion(new Question("breakup", "true", "Set a reminder to do something you love and limit any distractions."));
		db.questionDao().insertQuestion(new Question("breakup", "false", "What's your ex's name and one thing about them?"));
		db.questionDao().insertQuestion(new Question("breakup", "false", "Write a final note to your ex that they'll never see. Get it all off your chest. Tear it up when you feel ready. "));

		// Own It Expansion
		db.questionDao().insertQuestion(new Question("own it", "false", "When was the last time you felt complete (zen)? Who were you with? Where were you? What were you doing?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "How would you describe your relationship with yourself in one word?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What is your x-factor? Permission to brag."));
		db.questionDao().insertQuestion(new Question("own it", "false", "Who is a woman in your life that inspires you to be a better person? Why does she come to mind? (Send her a thank you)"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What still feels vulnerable in your life? What does owning it look like for you?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "Who from your past would you want to reunite with? What would you ask them?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What's the hardest truth you've had to own this past year? What has owning it taught you?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "How has your definition of success evolved over time? What is your definition of it today? Get specific."));
		db.questionDao().insertQuestion(new Question("own it", "false", "Who in your life do you want to build a deeper relationship with? (Be direct and tell them)"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What question have you avoided asking someone? What question have you avoided asking yourself?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What area of your life have you made the most progress in recently? Get specific. Take a moment to congratulate yourself."));
		db.questionDao().insertQuestion(new Question("own it", "false", "What part(s) of yourself have you been minimizing that you're ready to finally own? Permission to own it."));
		db.questionDao().insertQuestion(new Question("own it", "false", "Who do you feel misunderstood by? What do you wish they would come to understand?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What lesson in love took you the longest to learn?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What act of kindness from a stranger will you never forget?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What has brought you the most unexpected joy recently?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What has your heart been telling you that you've been ignoring?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What have you not forgiven that hurts you the most?"));
		db.questionDao().insertQuestion(new Question("own it", "true", "Set an intention for today, this month and this year. Write it out."));
		db.questionDao().insertQuestion(new Question("own it", "false", "What have you envied in the past that you can laugh at now?"));
		db.questionDao().insertQuestion(new Question("own it", "true", "Spend at least 5 minutes in nature today. Be still and observe. What did you notice?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What door are you most grateful for closing that felt like the end of the world at the time? What did it open for you?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What do you wish you had the courage to do? To say?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What blame have you been placing on someone else that you can take some accountability for?"));
		db.questionDao().insertQuestion(new Question("own it", "false", "What have you become newly aware of within yourself recently? Get specific."));
		db.questionDao().insertQuestion(new Question("own it", "true", "Write down 3 things you're most grateful for in this present moment. Big or small."));










	}


}