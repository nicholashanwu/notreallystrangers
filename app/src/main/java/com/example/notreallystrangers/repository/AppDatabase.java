package com.example.notreallystrangers.repository;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.notreallystrangers.model.Question;

@Database(entities = {Question.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

	public abstract QuestionDao questionDao();

	private static volatile AppDatabase INSTANCE;

	public static AppDatabase getInstance(Context context) {
		if (INSTANCE == null) {
			synchronized (AppDatabase.class) {
				if (INSTANCE == null) {
					try {


						INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
								AppDatabase.class,
								"database_name").
								fallbackToDestructiveMigration().
								allowMainThreadQueries().
								build();

						prepopulateData(INSTANCE);

						System.out.println("prepopulated");


//						INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
//								AppDatabase.class,
//								"database_name").
//								fallbackToDestructiveMigration().
//								createFromAsset("database_name").
//								allowMainThreadQueries().
//								build();
//
//						System.out.println("created from asset");

					} catch (Exception e) {

					}

				}
			}
		}
		return INSTANCE;
	}

	@NonNull
	@Override
	protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
		return null;
	}

	@NonNull
	@Override
	protected InvalidationTracker createInvalidationTracker() {
		return null;
	}

	@Override
	public void clearAllTables() {

	}

	private static void prepopulateData(AppDatabase db) {

//		AppDatabase db = AppDatabase.getInstance(this);

//		front: "Do you really know your best friend? How well do you know your mother, or sister, or anyone else close to you?",
//				"WNRS is a purpose driven card game. 3 carefully crafted levels that allow you to deepen your existing relationships and create new ones.",
//				"Ready?"

//		back: "What's more romantic than being understood?"


		//Base pack level 1
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What was your first impression of me?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What do you think is the hardest part of what I do for a living?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What reality show do you think I'm most likely to binge watch? Explain."));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What does my style tell you about me?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do I seem like a cat or dog person?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do I seem like more of a creative type or analytical type? Explain."));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "How many speeding tickets do you think I've gotten in my life?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do I seem like someone who would get a name tattooed on myself? Why or why not?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What do you think I'm most likely to splurge on?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Finish the sentence: Just by looking at you I'd think _________."));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What character would I play in a movie?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do I remind you of anyone?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Who do you think my celebrity crush is?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do you think I've ever checked an ex's phone for evidence?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What's the first thing you noticed about me?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What do you think my go-to karaoke song is?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "If you were to buy me a present, knowing nothing about me other than what I look like, what would it be?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What does my phone wallpaper tell you about me?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do I look kind? explain."));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do you think I fall in love easily? Why or why not?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "As a child, what do you think I wanted to be?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do you think I'm usually early, on time, or late to events? Explain."));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What subject do you think I thrived in at school? Did I fail any?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What compliment do you think I hear the most?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do I seem like a morning person or a night owl? Why?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What fast food restaurant do you think I'm most likely to drive through? What's my order?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What is my body language telling you right now?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What does my Instagram tell you about me?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do you think plants thrive or die in my care? Explain."));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "On a scale of 1-10, how messy do you think my car is? 1 being cleanest, 10 complete disaster. Explain."));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do you think I was popular in school? explain."));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do you think I've ever been fired from a job? If so, what for?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "Do you think I intimidate others? Why or why not?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "How likely am I to go camping? How high maintenance is my set up?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What about me is most strange or unfamiliar to you?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "What about me intrigues you?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "false", "If Myspace were still a thing what would my profile song be?"));

		//Base pack level 1 wildcards
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Maintain eye contact for thirty seconds. What did you notice?\""));
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Close your eyes. What color is my shirt?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Ask and answer the next question in a different accent. (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Draw a picture together. (30 seconds)"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Write down something others would never guess about you just by looking at you. (Both players) Compare."));
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Draw a portrait of each other to the best of your ability. (1 minute) Then exchange."));
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Think of your favorite brand of cereal. On the count of three say your answers out loud! (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Think of your favorite childhood tv show of all time. On the count of three, say it out loud! (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Rate your dancing skills on a scale of 1-10. On the count of three, say your answers out loud! (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Close your eyes. What color are my eyes?"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "true", "Make an assumption about me. (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 1, "reminder", "Let go of your attachment to the outcome."));

		//Base pack level 2
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "How can you become a better person?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What's your mother's name? And the most beautiful thing about her?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "If you could get to know someone in your life on a deeper level, who would it be and why?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What's the most pain you've ever been in that wasn't physical?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "Are you missing anyone right now? Do you think they are missing you too?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What is a dream you've let go of?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What is the last thing you lied to your mother about?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "Have you ever told someone I love you but didn't mean it? If so, why?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What are you still trying to prove to yourself?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "Are you lying to yourself about anything?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What is your 1st love's name and the reason you fell in love with them?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What part of your life works? What part of your life hurts?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What lesson took you the longest to unlearn?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "If you have, when was the moment you realized you weren't invincible?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "Do you think the image you have of yourself matches the image people see you as?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "If you could have it your way: Who would you be with? Where would you be? And what would you be doing?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "Is there a feeling you miss?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What's been your happiest memory this past year?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "When was the last time you surprised yourself?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "How would you describe the feeling of being in love in one word?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What's your father's name? And tell me one thing about him."));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What are you more afraid of, failure or success? And why?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What has been your earliest recollection of happiness?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What do you crave more of?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What is a compliment you wish you received more frequently?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "Have you changed your mind about anything recently?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "Describe your perfect day!"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What is the most unexplainable thing that's ever happened to you?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What would your younger self not believe about your life today?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What is something you wouldn't want to change about yourself?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What title would you give this chapter in your life?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "Has a stranger ever changed your life?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "How are you, really?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "When you're asked how are you, how often do you answer truthfully?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "Finish the sentences: strangers would describe me as _________. Only I know that I am _________."));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What question are you trying to answer most in your life right now?"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "false", "What's been the best compliment a stranger has ever given you?"));

		//Base pack level 2 wildcards
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Staring contest. First to blink must reveal a personal problem and ask your partner for advice on how they might handle it."));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Ask a question you'd be too afraid to ask. Something you wouldn't dare to ask."));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Call someone you admire and tell them why you appreciate them! (Put on speaker phone)"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Admit something."));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Questions are an art form. Create your own question."));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Think of something you strongly dislike that most people love. On the count of three, say it out loud! (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Swap seats with your partner."));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Show the first photo in your camera roll. Explain."));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Create a secret handshake."));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Sing the chorus of your favorite song of all time. Get into it!"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Draw your current mood. Then compare. (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Both players write an embarrassing fun fact about yourselves. Play a game of rock, paper, scissors. Loser must reveal!"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Both players write the most important things in life to you. (30 seconds) Compare."));
		db.questionDao().insertQuestion(new Question("base pack", 2, "true", "Press shuffle on your music library. Explain the first song that comes up!"));
		db.questionDao().insertQuestion(new Question("base pack", 2, "reminder", "Be more interested in understanding others than being understood."));

		//Base pack level 3
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "How would you describe me to a stranger?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What about me most surprised you?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What would be the perfect gift for me?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What do you admire most about me?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "Based on what you learned about me, what book would you recommend I read?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What about me is hardest for you to understand?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What am I most qualified to give advice about?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What is one thing you think I can do that would dramatically improve my life?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "If we were in a band, what would our name be?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What can I help you with?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "Based on what you learned about me, does my social media accurately reflect who I am? Why or why not?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What do I need to hear right now?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "Based on what you know about me, do you have any Netflix recommendations?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What do you think my superpower is?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What is a lesson you will take away from our conversation?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "When in this game did you feel most connected to me?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "How does one earn your vulnerability? Have I earned it? How can I earn more?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What do you think our most important similarity is?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What can we create together?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "In one word, how would you describe our conversation?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What answer of mine made you light up?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "When this game is over, what will you remember about me?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What parts of yourself do you see in me?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "How do our personalities complement each other?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "Why do you think we met?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What do you think my weakness is?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What would make you feel closer to me?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What do you think I fear the most?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What has this conversation taught you about yourself?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What do you think I should know about myself that perhaps I'm unaware of?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What do you think my defining characteristic is?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "Do you believe everyone has a calling? If so, do you think I've found mine?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "What do you recommend I let go of, if anything?"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "false", "In one word, describe how you feel right now."));

		//Base pack level 3 wildcards
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Give your partner a hug. Not the crappy kind. A warm fluffy one."));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Write down one thing you want to let go of this year. Read out loud, then rip up together. (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Swap a song suggestion your partner may enjoy. (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Dare your partner to do something outside of their comfort zone in the next week. (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Admit something."));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Give your partner a compliment you don't think they hear enough."));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Write a song about your partner. (30 seconds) Then sing it aloud, get into it! (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Scroll through each other's Instagrams. Find the picture you feel best represents your partner's essence and comment on why."));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Both players share something you're most grateful for in this current moment."));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Play a round of rock, paper, scissors. Winner can ask their partner anything. Loser must answer."));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Give each other nicknames!"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Create your own question. Make it count."));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Take a selfie together."));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Write down a goal for this year. Fold & exchange. Hold each other accountable. (Both players)"));
		db.questionDao().insertQuestion(new Question("base pack", 3, "true", "Both players write a note to your younger selves. (1 minute) Option to compare."));

		db.questionDao().insertQuestion(new Question("base pack", 3, "final", "Each player write a message to the other. Fold and exchange. Open only once you two have parted."));

		// Honest Dating

		// front: "How honest are your dating experiences, really?",
		//    "Dating doesn't have to be a game, it can be as simple as getting to know another human being. Discovering compatibility with another person is only possible when we show up as our true selves, but we have to be willing to let our true selves be seen.",
		//    "This dating expansion pack can be added to the original WNRS card game. Each player alternates between asking and answering each question. How do you win? Stay honest.",
		//    "Ready?"

		//	back: "What if dating was as simple as getting to know another human being?",
		//    "No games, just this one."

		// Honest Dating Expansion level 1
		db.questionDao().insertQuestion(new Question("honest dating", 1, "false", "Just by looking at me, what would you think I do for a living?"));
		db.questionDao().insertQuestion(new Question("honest dating", 1, "false", "What do you think my ideal type is? Describe in detail."));
		db.questionDao().insertQuestion(new Question("honest dating", 1, "false", "What do you think my favourite drink is?"));
		db.questionDao().insertQuestion(new Question("honest dating", 1, "false", "How many relationships do you think I've been in? How many times do you think I've been in love? Explain."));
		db.questionDao().insertQuestion(new Question("honest dating", 1, "false", "What do you think I was like in high school?"));
		db.questionDao().insertQuestion(new Question("honest dating", 1, "false", "Do you think I'm on good terms with my exes? Why or why not?"));
		db.questionDao().insertQuestion(new Question("honest dating", 1, "false", "What was the first thing you noticed about me when we met in person?"));

		db.questionDao().insertQuestion(new Question("honest dating", 1, "true", "Write down your 3 favorite physical features of your partner and read them out loud. Both players."));

		// Honest Dating expansion level 2
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What are you feeling a lot of lately?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "Do you have any unconventional opinions when it comes to romantic relationships?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "I know I really like someone when ______"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "Has sex ever been a dealbreaker in past relationships? Explain."));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "When's the last time you ghosted someone and why?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What about my intrigued you besides my physical appearance?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What do your friends know about me, if anything?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What part of your job energizes you the most? Leaves you the most drained?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "How has your view of love evolved over time, if at all?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "How do you define 'dating'?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "Describe your worst heartbreak. What did it teach you?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "Have you ever considered an open relationship? Why or why not?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "Who in your life do you feel most yourself around? Why?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "Which one of your parents' personality traits do you want to keep? Let go of?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What is something new you've learned about yourself recently?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What's the hardest part about dating you?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What are you overthinking right now?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What have you tolerated from others in the past that you no longer have space for?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What dating advice would you give your younger self?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "How emotionally available do you currently feel? Explain."));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What has being single taught you about yourself?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What was the worst date you've ever been on and why?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What about you has felt repeatedly misunderstood by others, if anything?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What's a non-negotiable in your life?"));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "false", "What did the people who raised you teach you about love? How has that shaped your views on love today?"));

		db.questionDao().insertQuestion(new Question("honest dating", 2, "true", "Write down your top 2 relationship deal breakers. Both players. Compare."));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "true", "Write down your favorite song lyric you can think of off the top of your head. Compare and explain."));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "true", "Share something you typically wouldn't on a first date. Both players."));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "true", "Give each other tattoos with the nearest pen or marker. 30 seconds."));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "true", "Write down the top 3 most important qualities in a romantic partner. Both players. Compare."));
		db.questionDao().insertQuestion(new Question("honest dating", 2, "true", "Think of the most underrated show streaming currently. On the count of 3, say your answers out loud. Both players."));

		// Honest dating expansion level 3
		db.questionDao().insertQuestion(new Question("honest dating", 3, "false", "What song should I listen to on my way home?"));
		db.questionDao().insertQuestion(new Question("honest dating", 3, "false", "Based on what you've learned about me, what do you think I'm looking for romantically?"));
		db.questionDao().insertQuestion(new Question("honest dating", 3, "false", "On a scale of 1-10, how emotionally available do I seem? Explain."));
		db.questionDao().insertQuestion(new Question("honest dating", 3, "false", "What mannerisms of mine stood out the most to you, if any?"));
		db.questionDao().insertQuestion(new Question("honest dating", 3, "false", "How would you describe our date in one word?"));
		db.questionDao().insertQuestion(new Question("honest dating", 3, "false", "If you were to write my dating profile bio, what would it say?"));
		db.questionDao().insertQuestion(new Question("honest dating", 3, "false", "What part of my personality isn't showcased online?"));
		db.questionDao().insertQuestion(new Question("honest dating", 3, "false", "What do you find most attractive about me that isn't physical?"));

		db.questionDao().insertQuestion(new Question("honest dating", 3, "true", "Look through each other's social media. Pick your favourite photo. Compare and explain."));
		db.questionDao().insertQuestion(new Question("honest dating", 3, "true", "Write your partner a text outside of your comfort zone at the end of this date. Both players."));

		db.questionDao().insertQuestion(new Question("honest dating", 3, "final", "Ask literally anything. Permission to go there."));





		// Inner Circle Expansion

		//	back: "There's always another layer to anyone you think you know"

		// Inner Circle level 1
		db.questionDao().insertQuestion(new Question("inner circle", 1, "false", "What do you think I'm most sensitive to?"));
		db.questionDao().insertQuestion(new Question("inner circle", 1, "false", "What do you think my main love language is?"));

		// Inner Circle level 2
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "What's the hardest truth you had to face this past year?"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "What's brought you the most unexpected joy recently?"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "Who in your life do you feel you can be most vulnerable with? Explain."));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "Have I ever challenged your worldview? How so."));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "What is a moment in our relationship that you felt undeniably loved?"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "How can I add 1% more happiness to your life?"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "A perfect day together would be ______"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "What's the most fun you can remember having with me recently?"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "How can I best support you in this chapter in your life?"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "What's a feeling you're uncomfortable sharing with me?"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "What are you currently working through that I don't see?"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "When's the last time you felt lucky to be you?"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "When's the last time I surprised you?"));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "false", "When was the last time you felt most yourself?"));


		db.questionDao().insertQuestion(new Question("inner circle", 2, "true", "Sing your favorite song lyrics you can think of off the top of your head. Both players."));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "true", "Ask me something you think is off limits. Both players."));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "true", "Draw your favourite memory with me. Both players. Compare."));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "true", "Set an intention for the coming week. Stick to it. Both players."));
		db.questionDao().insertQuestion(new Question("inner circle", 2, "true", "Finish the sentence: When I'm hurt I _________. Both players."));

		// Inner Circle level 3
		db.questionDao().insertQuestion(new Question("inner circle", 3, "false", "What are you proudest of me for?"));
		db.questionDao().insertQuestion(new Question("inner circle", 3, "false", "What's something we should celebrate together?"));
		db.questionDao().insertQuestion(new Question("inner circle", 3, "false", "You've shown me ______ about myself."));
		db.questionDao().insertQuestion(new Question("inner circle", 3, "final", "Finish the sentence: Thank you for ______. Both players."));



		// Relationship Expansion

		// "How well do you know your partner? How well do they know you?",
		//    "Communication creates partnership, but that requires sharing your truth and being open to receiving theirs. Especially when it's uncomfortable.",
		//    "This relationship expansion pack can be added to the original WNRS card game. Remember there are two ways to play this game. Either play safe, or play to grow. The second is how you win.",
		//    "Ready?"

		// back: "What's more romantic than being understood?"

		// level 1
		db.questionDao().insertQuestion(new Question("relationship", 1, "false", "How important do you think birthdays and holidays are to me? Explain."));
		db.questionDao().insertQuestion(new Question("relationship", 1, "false", "On a scale of 1-10, how open do you feel I am with you? Explain."));
		db.questionDao().insertQuestion(new Question("relationship", 1, "false", "Who do you think was more nervous on our first date? Explain."));
		db.questionDao().insertQuestion(new Question("relationship", 1, "false", "What was the first thing you noticed about me?"));
		db.questionDao().insertQuestion(new Question("relationship", 1, "false", "What do you think my perfect date night would be?"));
		db.questionDao().insertQuestion(new Question("relationship", 1, "false", "What assumption did you make about me that turned out to be false?"));

		//level 2
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What did our worst argument teach you?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What has this relationship taught you about yourself?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What's one small way I can be a better partner?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What do you wish we did more of?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What have you been extra sensitive to lately?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What's the most important lesson a past relationship has taught you?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What are you currently working through that I don't see, if anything?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "Have I helped you change your mind about anything?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "How do I show that I love you without telling you?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What's one small way we can improve our sex life?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What feelings are hard for you to communicate to me? How can I make it easier?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What about you feels hardest to love?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "How have you seen me grow in this relationship?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What have I helped you appreciate about yourself?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What recent experience made you feel closer to me?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What immediately attracted you to me? What attracted you more over time?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What fear do you think holds me back the most?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What song best describes our relationship?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "How does our age difference, or lack thereof, affect us, if at all?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What's the most attractive thing I do without realizing it?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What's the most romantic thing I've done for you recently?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "How does one earn your trust? Have I earned it? How can I earn more?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "Finish the sentence: Thank you for accepting ______"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What would a day of completely spoiling me look like?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What made you fall in love with me?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "Do you think my job affects me positively or negatively? How does it affect us?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "Are there any insecurities from previous relationships that you carried into this one? If so, what are they?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "When was the last time I hurt you, perhaps unintentionally?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "What's the best gift I've given you? Material and immaterial?"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "The hardest thing for me to reveal about myself to you was ______"));
		db.questionDao().insertQuestion(new Question("relationship", 2, "false", "How do our strengths and weaknesses complement each other?"));

		db.questionDao().insertQuestion(new Question("relationship", 2, "true", "Write a love song for your partner. 1 minute. Perform for each other."));
		db.questionDao().insertQuestion(new Question("relationship", 2, "true", "Write down the top 3 things your partner does that makes you feel most loved. Both players. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", 2, "true", "On a scale of 1-10 write down the importance of having kids to you. Both players. Compare and explain. "));
		db.questionDao().insertQuestion(new Question("relationship", 2, "true", "Write down one small way you can be better in this relationship. Both players. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", 2, "true", "Draw one of your favourite memories from our relationship. Both players. 30 seconds. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", 2, "true", "Write down the title you would give this chapter of our relationship. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", 2, "true", "Ask your partner something you've never asked them before. Both players."));
		db.questionDao().insertQuestion(new Question("relationship", 2, "true", "Write a short guide on how to love your partner well. 30 seconds. Compare."));

		//level 3
		db.questionDao().insertQuestion(new Question("relationship", 3, "false", "What answer of mine surprised you the most throughout this game, if any?"));
		db.questionDao().insertQuestion(new Question("relationship", 3, "false", "Is there anything you still don't know about me that you wish you did?"));
		db.questionDao().insertQuestion(new Question("relationship", 3, "false", "Thank you for helping me realize ______ about myself."));
		db.questionDao().insertQuestion(new Question("relationship", 3, "false", "What don't I give myself credit for?"));
		db.questionDao().insertQuestion(new Question("relationship", 3, "false", "What goal would feel best for you to accomplish this year? How can I support you in that?"));
		db.questionDao().insertQuestion(new Question("relationship", 3, "false", "What about our relationship are you proudest of?"));
		db.questionDao().insertQuestion(new Question("relationship", 3, "false", "What did this conversation teach you about our relationship? What did it teach you about yourself?"));

		db.questionDao().insertQuestion(new Question("relationship", 3, "true", "Write down the top 3 things you're most grateful for in your partner. 30 seconds. Compare."));
		db.questionDao().insertQuestion(new Question("relationship", 3, "final", "Write down your intention for our relationship. Compare."));



		//Breakup Expansion

		// back: "The end of a relationship is the beginning of a new one... with yourself"

		//"Breakups are important. They teach us about ourselves. They show us what we want and what we don't. They allow us to move away from what isn't for us – and closer to what is. They even show us parts of ourselves we need to break up.",
		//    "We've created this edition as a tool to self-reflect after a breakup. Journal your answers or play with a friend who can be a soundboard for your healing process. The end of a relationship is the beginning of a new one… with yourself.",
		//    "Ready?"

		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What has helped you heal from a heartbreak in the past, besides time and meeting someone new?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "After your most recent breakup, what song did you play on repeat?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Picture you're talking to your ex. Finish the sentence: You hurt me, but thank you for _________."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What have all your previous relationships had in common that you'd like to avoid moving forward, if anything."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Did their love leave you feeling good most of the time? If not, what was the dominant feeling they left you with?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Were you lying to yourself about anything during your last relationship? If so, what was it?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What did your last relationship make you appreciate about yourself?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What are 3 things you can appreciate about being single (even if it feels hard)?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Are you talking to other people currently? If so, are you seeking a real connection or needing a distraction?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What part of your last relationship worked? What part of it hurt?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Do you want closure or just an excuse to talk to them? Explain."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "How can you show up for yourself when no one else can?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "How has your last relationship prepared you for your next one?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What parts of yourself do you need to break up with?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What would feel best to fully accept in this current moment?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Who in your life has been treating you right lately? Let them know before moving on to the next card. (Exes not included.)"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "How did you get over your first love?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Were you more attracted to their potential or who they really were? Explain."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What title would you give this chapter of your healing process?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Would you encourage your future child to be in a relationship like your last? Why or why not?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What story about love have you been telling yourself that no longer serves you? How would you like to change that story?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What can you be doing now with all the energy you were putting into them?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What's the best lesson an ex has ever taught you?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What were you over-rationalizing throughout your last relationship, if anything?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Do you have a limiting belief about love? Where do you think it stems from? How can you challenge this belief?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What can you do for yourself today, even if you don't enjoy it, to make future you happy?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Did your last relationship reveal some toxic traits in yourself that you'd like to work on? If so, what are they?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What is your heart telling you? What is your mind telling you?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What do you want to receive from a future relationship? What do you want to give? Get specific."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Were there any patterns in your previous relationship that you're newly aware of? If so, what were they?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Significant other aside, when you visualize coming home to a life you love, what does that look like? Get specific."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What was the biggest turning point in your dating life?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What validation are you seeking from a partner? How can you give it to yourself?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Imagine you're talking to your ex. Finish the sentence: I'm sorry for _________."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What's your ex's name and one thing about them?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "Did you like your ex as a person? Or were you just in love? Explain."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What feels unique about this heartbreak, if anything?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "What advice would you give a friend or loved one going through a similar situation as you?"));
		db.questionDao().insertQuestion(new Question("breakup", 1, "false", "If you could ask your ex one thing and get a completely honest answer, what would it be?"));

		db.questionDao().insertQuestion(new Question("breakup", 1, "true", "Write down 3 things you love about yourself that aren't physical."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "true", "Write a note to your future self. Fold and place somewhere safe. Set a reminder to open 3 months from today."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "true", "Write a stand up routine about your last relationship. 1 minute. Read out loud."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "true", "Write down 3 things you love about yourself physically. Permission to brag."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "true", "Set a time this week to do something you love and limit any distractions."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "true", "Write down 3 things you wouldn't change about this current moment."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "true", "Draw how your heart feels today."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "true", "Write down 3 things about your last relationship that you will no longer accept moving forward. Get specific."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "reminder", "Breakups skew your memory. Your ex was not perfect. You will feel better."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "reminder", "Don't let your loneliness take you back to people who repeatedly hurt you."));
		db.questionDao().insertQuestion(new Question("breakup", 1, "reminder", "Your love life is one area of your life. Don't forget to nurture the rest."));

		db.questionDao().insertQuestion(new Question("breakup", 1, "final", "Write a final note to your ex that they'll never see. Get it all off your chest. Tear when you feel ready."));






		// Own It Expansion

		// "What if your biggest insecurities were your greatest strengths? If you own it. They are!",
		//    "To \"own it\" is to accept the truth and deal with it, however unpleasant it may be. We're Not Really Strangers and DVF came together to create an expansion pack all about owning the person we are today and the person we are becoming.",
		//    "To play, journal your answers or explore this deck with a loved one who's willing to dig deep.",
		//    "Owning who we are is the best thing we can do for ourselves and the people in our lives.",
		//    "Ready?"


		db.questionDao().insertQuestion(new Question("own it", 1, "false", "When was the last time you felt complete (zen)? Who were you with? Where were you? What were you doing?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "How would you describe your relationship with yourself in one word?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What is your x-factor? Permission to brag."));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "Who is a woman in your life that inspires you to be a better person? Why does she come to mind? (Send her a thank you)"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What still feels vulnerable in your life? What does owning it look like for you?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "Who from your past would you want to reunite with? What would you ask them?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What's the hardest truth you've had to own this past year? What has owning it taught you?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "How has your definition of success evolved over time? What is your definition of it today? Get specific."));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "Who in your life do you want to build a deeper relationship with? (Be direct and tell them)"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What question have you avoided asking someone? What question have you avoided asking yourself?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What area of your life have you made the most progress in recently? Get specific. Take a moment to congratulate yourself."));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What part(s) of yourself have you been minimizing that you're ready to finally own? Permission to own it."));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "Who do you feel misunderstood by? What do you wish they would come to understand?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What lesson in love took you the longest to learn?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What act of kindness from a stranger will you never forget?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What has brought you the most unexpected joy recently?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What has your heart been telling you that you've been ignoring?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What have you not forgiven that hurts you the most?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What have you envied in the past that you can laugh at now?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What door are you most grateful for closing that felt like the end of the world at the time? What did it open for you?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What do you wish you had the courage to do? To say?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What blame have you been placing on someone else that you can take some accountability for?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "false", "What have you become newly aware of within yourself recently? Get specific."));

		db.questionDao().insertQuestion(new Question("own it", 1, "true", "Spend at least 5 minutes in nature today. Be still and observe. What did you notice?"));
		db.questionDao().insertQuestion(new Question("own it", 1, "true", "Set an intention for today, this month and this year. Write it out."));
		db.questionDao().insertQuestion(new Question("own it", 1, "final", "Write down 3 things you're most grateful for in this present moment. Big or small."));



		// Quarantine Expansion

		// back: "Physically distant. Emotionally Connected"

		// level 1
		db.questionDao().insertQuestion(new Question("quarantine", 1, "false", "What time do you think I've been waking up these days?"));
		db.questionDao().insertQuestion(new Question("quarantine", 1, "false", "What do you think I've been binge watching lately?"));
		db.questionDao().insertQuestion(new Question("quarantine", 1, "false", "Do you think I'm mainly cooking or ordering delivery?"));
		db.questionDao().insertQuestion(new Question("quarantine", 1, "false", "What do you think has been my go to snack during this time?"));

		//level 2
		db.questionDao().insertQuestion(new Question("quarantine", 2, "false", "When quarantining is behind us, what change do you want to make in your day to day life if any?"));
		db.questionDao().insertQuestion(new Question("quarantine", 2, "false", "What can you create during this time with exactly what you have? Big or small."));
		db.questionDao().insertQuestion(new Question("quarantine", 2, "false", "What's been the kindest thing someone has done for you during this time? What's been the kindest thing you've done for yourself?"));
		db.questionDao().insertQuestion(new Question("quarantine", 2, "false", "What would feel best to fully accept in this current moment?"));
		db.questionDao().insertQuestion(new Question("quarantine", 2, "false", "What are you most excited for today? Big or small?"));
		db.questionDao().insertQuestion(new Question("quarantine", 2, "false", "What has been the hardest part of all this for you? Permission to vent."));
		db.questionDao().insertQuestion(new Question("quarantine", 2, "false",  "What movie do you wish you'd never seen so you can watch it for the first time during quarantine?"));

		db.questionDao().insertQuestion(new Question("quarantine", 2, "true", "Staring contest. First to smile must reveal what their kitchen looks like in the current moment."));
		db.questionDao().insertQuestion(new Question("quarantine", 2, "true", "Share your most played song at the moment"));
		db.questionDao().insertQuestion(new Question("quarantine", 2, "true", "Share your screen time. Both players."));

		//level 3
		db.questionDao().insertQuestion(new Question("quarantine", 3, "false", "If you made a playlist for me, what 3 songs would be on it?"));
		db.questionDao().insertQuestion(new Question("quarantine", 3, "false", "What do I need to watch as soon as I have the time?"));
		db.questionDao().insertQuestion(new Question("quarantine", 3, "false", "How can I best be there for you during this chapter?"));

		db.questionDao().insertQuestion(new Question("quarantine", 3, "true", "Who's been on your mind lately? Send them a text letting them know you're thinking of them. Both players."));

		db.questionDao().insertQuestion(new Question("quarantine", 3, "final", "Each player write a virtual message to your partner. Press send once you two have hung up."));










	}
}
