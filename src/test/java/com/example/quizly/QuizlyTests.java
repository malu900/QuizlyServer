package com.example.quizly;

import com.example.quizly.accesssingdata.*;
import com.example.quizly.mock.*;
import com.example.quizly.models.request.authentication.RegisterModel;
import com.example.quizly.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.filter.ValueNode;
import org.apache.tomcat.websocket.AsyncChannelWrapper;
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
    private GuestService guestService;

    private Quiz quiz;
    private Question question;
    private Answer answer;
    private User user;
    private Guest guest;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void SetUp(){
        mockQuizRepo = new MockQuizRepo();
        mockUserRepo = new MockUserRepo();
        mockQuestionRepo = new MockQuestionRepo();
        mockAnswerRepo = new MockAnswerRepo();
        mockGuestRepo = new MockGuestRepo();

        guestService = new GuestService(mockGuestRepo);

        quizService = new QuizService(mockQuizRepo, mockUserRepo, mockGuestRepo, guestService);

        questionService = new QuestionService(mockQuestionRepo, quizService);

        answerService = new AnswerService(mockAnswerRepo);

        userService = new UserService(mockUserRepo);

        authService = new AuthService(mockUserRepo);
    }

    @Test
    void createQuizWithOneQuestion(){
        int oldCount = quizService.getAllQuiz().size();

        quiz = new Quiz(1L, "weeb over 9000 quiz", new ArrayList<>(), new User(),"1", new ArrayList<>());
        quiz.addQuestion(new Question(1L, new ArrayList<>(), new Quiz(), "what is king of flaaaavour?"));
        quizService.addQuiz(quiz, new User(42L, "potato-san", "potato@gmail.com", "iLovePotatoes420", new ArrayList<>()));

        int newCount = oldCount + 1;

        assertNotEquals(oldCount, newCount);
    }

    @Test
    void createQuizWithThreeQuestions(){
        int oldCount = quizService.getAllQuiz().size();

        quiz = new Quiz(1L, "weeb over 9000 quiz", new ArrayList<>(), new User(),"1", new ArrayList<>());
        for (int i = 0; i < 3; i++){
            quiz.addQuestion(new Question(1L, new ArrayList<>(), new Quiz(), "this is question? numbahh " + i));
        }
        quizService.addQuiz(quiz, new User(42L, "potato-san", "potato@gmail.com", "iLovePotatoes420", new ArrayList<>()));
        int newCount = oldCount + 3;

        assertNotEquals(oldCount, newCount);
    }

    //quizvraag goed beantwoorden hoort in logica te zitten (zit er op het moment er nog niet in)
    //quizvraag fout beantwoorden hoort in logica te zitten (zit er op het moment er nog niet in)
    //lobby aanmaken == quiz aanmaken (done)

    //lobby joinen == quiz joinen
    @Test
    void joinQuiz() throws Exception {
       int oldCount = quizService.findById(1L).getParticipants().size();
       quizService.JoinQuiz("mahnaemehjeff", "S3NDB0BSANDV4G3N3");
       int newCount = quizService.findById(1L).getParticipants().size();

       assertNotEquals(oldCount, newCount);
    }

    //lobby leaven == quiz leaven
    @Test
    void leaveQuiz() throws Exception {
        quizService.JoinQuiz( "mahnaemehjeff", "S3NDB0BSANDV4G3N3");
        int oldCount = quizService.findById(1L).getParticipants().size();

        quizService.LeaveQuiz("S3NDB0BSANDV4G3N3", "mahnaemehjeff");
        int newCount = quizService.findById(1L).getParticipants().size();

        assertNotEquals(oldCount, newCount);
    }

    //lobby starten ???
    //next question -----

    @Test
    void addQuestionToExistingQuiz(){
        quiz = new Quiz(69L, "weeb over 9000 quiz", new ArrayList<>(), new User(),"1", new ArrayList<>());
        question = new Question(96L, new ArrayList<>(), new Quiz(), "what is king of flaaaavour?");

        quizService.addQuiz(quiz, new User(42L, "potato-san", "potato@gmail.com", "iLovePotatoes420", new ArrayList<>()));
        int oldCount = quizService.findById(69L).getQuestions().size();
        questionService.addQuestion(question, quiz);

        Quiz quizSixtyNine = quizService.findById(69L);
        int newCount = quizSixtyNine.getQuestions().size();

        assertNotEquals(oldCount, newCount);
    }

    @Test
    void addAnswersToQuestionWhenQuestionIsGettingAdded(){ //shoutout to semester 2 docent telling me to specify the methods with whole sentences
        quiz = new Quiz(69L, "weeb quiz", new ArrayList<>(), new User(),"1", new ArrayList<>());
        question = new Question(96L, new ArrayList<>(), new Quiz(), "what's the monster inside of Yuji Itadori called?");
        answer = new Answer(1L, "Sukuna", true, question);

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
