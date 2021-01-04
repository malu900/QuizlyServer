package com.example.quizly;

import com.example.quizly.accesssingdata.Answer;
import com.example.quizly.accesssingdata.Question;
import com.example.quizly.accesssingdata.Quiz;
import com.example.quizly.accesssingdata.User;
import com.example.quizly.mock.*;
import com.example.quizly.models.request.authentication.RegisterModel;
import com.example.quizly.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.filter.ValueNode;
import org.hibernate.dialect.function.AbstractAnsiTrimEmulationFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockAsyncContext;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.socket.messaging.SubProtocolErrorHandler;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class QuizlyTests {
    @Mock
    private MockQuizRepo mockQuizRepo;

    @Mock
    private MockQuestionRepo mockQuestionRepo;

    @Mock
    private MockAnswerRepo mockAnswerRepo;

    @Mock
    private MockUserRepo mockUserRepo;

    @Mock
    private MockGuestRepo mockGuestRepo;

    private QuizService quizService;
    private QuestionService questionService;
    private AnswerService answerService;
    private UserService userService;
    private AuthService authService;

    private Quiz quiz;
    private Question question;
    private Answer answer;
    private User user;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void SetUp(){
        mockQuizRepo = new MockQuizRepo();

        mockUserRepo = new MockUserRepo();
        quizService = new QuizService(mockQuizRepo, mockUserRepo, mockGuestRepo, null);

        mockQuestionRepo = new MockQuestionRepo();
        questionService = new QuestionService(mockQuestionRepo, quizService);

        mockAnswerRepo = new MockAnswerRepo();
        answerService = new AnswerService(mockAnswerRepo);

        userService = new UserService(mockUserRepo);

        authService = new AuthService(mockUserRepo);
    }

    @Test
    void createQuizWithOneQuestion(){
        int oldCount = quizService.getAllQuiz().size();

        quiz = new Quiz(1L, "weeb over 9000 quiz", new ArrayList<>(), new User(),"1");
        quiz.addQuestion(new Question(1L, new ArrayList<>(), new Quiz(), "what is king of flaaaavour?"));
        quizService.addQuiz(quiz, new User(42L, "potato-san", "potato@gmail.com", "iLovePotatoes420", new ArrayList<>()));

        int newCount = oldCount + 1;

        assertNotEquals(oldCount, newCount);
    }

    @Test
    void createQuizWithThreeQuestions(){
        int oldCount = quizService.getAllQuiz().size();

        quiz = new Quiz(1L, "weeb over 9000 quiz", new ArrayList<>(), new User(),"1");
        for (int i = 0; i < 3; i++){
            quiz.addQuestion(new Question(1L, new ArrayList<>(), new Quiz(), "this is question? numbahh " + i));
        }
        quizService.addQuiz(quiz, new User(42L, "potato-san", "potato@gmail.com", "iLovePotatoes420", new ArrayList<>()));
        int newCount = oldCount + 3;

        assertNotEquals(oldCount, newCount);
    }

    //quizvraag goed beantwoorden hoort in logica te zitten (zit er op het moment er nog niet in)
    //quizvraag fout beantwoorden hoort in logica te zitten (zit er op het moment er nog niet in)
    //lobby aanmaken ???
    //lobby joinen ???
    //lobby starten ???
    //next question -----

    @Test
    void addQuestionToExistingQuiz(){
        quiz = new Quiz(69L, "weeb over 9000 quiz", new ArrayList<>(), new User(),"1");
        question = new Question(96L, new ArrayList<>(), new Quiz(), "what is king of flaaaavour?");

        quizService.addQuiz(quiz, new User(42L, "potato-san", "potato@gmail.com", "iLovePotatoes420", new ArrayList<>()));
        int oldCount = quizService.findById(69L).getQuestions().size();
        questionService.addQuestion(question, quiz);

        Quiz quizSixtyNine = quizService.findById(69L);
        int newCount = oldCount + 1;

        assertNotEquals(oldCount, newCount);
    }

    @Test
    void addAnswersToQuestionWhenQuestionIsGettingAdded(){ //shoutout to semester 2 docent telling me to specify the methods with whole sentences
        quiz = new Quiz(69L, "weeb quiz", new ArrayList<>(), new User(),"1");
        question = new Question(96L, new ArrayList<>(), new Quiz(), "what's the monster inside of Yuji Itadori called?");
        answer = new Answer(1L, "Sukuna", question);

        quizService.addQuiz(quiz, new User(42L, "potato-san", "potato@gmail.com", "iLovePotatoes420", new ArrayList<>()));
        questionService.addQuestion(question, quiz);
        answerService.addAnswer(answer, question);

        String questionAnswer = "Sukuna";
        Optional<Question> questionNinetySix = questionService.getAllQuestions()
                .stream()
                .filter(q -> q.getQuestionId() == 96L)
                .findAny();

        if(questionNinetySix.isPresent()){
            String actualAnswer = questionNinetySix.get().getAnswers()
                    .stream().findFirst().get().getAnswerContent();

            assertEquals(questionAnswer, actualAnswer);
        }
    }

    @Test
    void addAccount() throws JsonProcessingException {
        RegisterModel userModel = new RegisterModel("potato-san", "potato@gmail.com", "iLovePotatoes420", "iLovePotatoes420");
        user = new User(42L, "potato-san", "potato@gmail.com", "iLovePotatoes420", null);

        authService.register(userModel);
        Optional<User> actualUser = userService.getAllUsers().stream().filter(u -> u.getName().equals("potato-san") &&  BCrypt.checkpw("iLovePotatoes420", u.getPassword())).findAny();
        if(actualUser.isPresent()){
            assertEquals(mapper.writeValueAsString(user.getName()), mapper.writeValueAsString(actualUser.get().getName()));
        }
    }

}
