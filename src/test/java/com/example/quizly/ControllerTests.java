package com.example.quizly;

import com.example.quizly.accesssingdata.*;
import com.example.quizly.controller.*;
import com.example.quizly.mock.*;
import com.example.quizly.models.request.authentication.GuestModel;
import com.example.quizly.models.request.authentication.LoginModel;
import com.example.quizly.models.request.authentication.RegisterModel;
import com.example.quizly.models.response.Authentication.LoginResponse;
import com.example.quizly.models.response.Authentication.QuizREST;
import com.example.quizly.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import com.example.quizly.accesssingdata.*;
import com.example.quizly.controller.AuthController;
import com.example.quizly.mock.*;
import com.example.quizly.models.request.authentication.GuestModel;
import com.example.quizly.models.request.authentication.LoginModel;
import com.example.quizly.models.request.authentication.RegisterModel;
import com.example.quizly.models.response.Authentication.LoginResponse;
import com.example.quizly.models.response.Authentication.QuizREST;
import com.example.quizly.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

    @SpringBootTest
    public class ControllerTests {


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

        private AnswerController answerController;
        private AuthController authController;
        private QuestionController questionController;
        private QuizController quizController;
        private SocketController socketController;


        @BeforeEach
        public void SetUp(){

            guestService = mock(GuestService.class);

            quizService = mock(QuizService.class);

            questionService = mock(QuestionService.class);

            answerService = mock(AnswerService.class);

            userService = mock(UserService.class);

            authService = mock(AuthService.class);

            answerController = new AnswerController(answerService, questionService);

            authController = new AuthController(authService);

            questionController = new QuestionController(questionService, quizService, answerService);

            quizController = new QuizController(quizService, userService, questionService);

            socketController = new SocketController(quizService);

        }

        @Test
        void testGetAllAnswers(){
            List<Answer> answers = new ArrayList<>();
            answers.add(new Answer(1L, "pallet town", true, new Question()));
            answers.add(new Answer(1L, "to stop making people do drugs", false, new Question()));
            answers.add(new Answer(1L, "lazy town", false, new Question()));
            given(answerService.getAllAnswers()).willReturn(answers);

            ResponseEntity entity = answerController.getAllAnswers();
            List<Answer> responseAnswers = (List<Answer>) entity.getBody();
            assertTrue(responseAnswers.size() == 3);
        }

        @Test
        void testAddNewAnswer() throws Exception {
            Question question = new Question();
            given(questionService.findById(any(long.class))).willReturn(question);
            given(answerService.addAnswer(any(Answer.class), any(Question.class))).willReturn("Succesvol");

            String expectedResponse = "Saved";
            String response = answerController.addNewAnswer(new Answer(), 2L);
            assertTrue(response.equals(expectedResponse));
        }

        @Test
        void testUpdateAnswer(){
            given(answerService.updateAnswer(any(Answer.class))).willReturn("Succesvol");
            String expectedResponse = "Succesvol";
            Answer answer = new Answer();
            ResponseEntity entity = answerController.updateAnswer(answer);
            assertTrue(entity.getBody().equals(expectedResponse));
        }

        @Test
        void testRegister(){
            given(authService.register(any(RegisterModel.class))).willReturn("SuccesFull");
            RegisterModel model = new RegisterModel();
            model.setName("Test");
            model.setEmail("test.test@test.nl");
            model.setPassword("Password");
            model.setSecondPassword("Password");
            ResponseEntity entity = authController.registerUser(model);
            assertTrue(entity.getBody().equals("SuccesFull"));
        }

        @Test
        void testLogin(){
            LoginResponse expectedResponse = new LoginResponse();
            expectedResponse.setUserId(1L);
            expectedResponse.setName("Testname");
            LoginModel model = new LoginModel();
            model.setEmail("test.test@test.nl");
            model.setPassword("Password");
            given(authService.login(any(LoginModel.class))).willReturn(expectedResponse);
            LoginResponse response = authController.login(model);
            assertTrue(response.equals(expectedResponse));
        }

        @Test
        void testRegisterGuest() throws Exception {
            GuestModel model = new GuestModel();
            model.setCode("123");
            model.setName("testname");
            given(authService.registerGuest(model)).willReturn("SuccesFull");
            String expectedResponse = "Successfully added guest";
            String response = authController.registerGuest(model);
            assertTrue(response.equals(expectedResponse));
        }
        @Test
        void testGetAllQuestions(){
            List<Question> questions = new ArrayList<>();
            questions.add(new Question(1L, new ArrayList<>(), new Quiz(), "what is giorno giovanna's dream?"));
            questions.add(new Question(2L, new ArrayList<>(), new Quiz(), "how old is ash ketchum from pallet town?"));
            questions.add(new Question(3L, new ArrayList<>(), new Quiz(), "is that a jojo reference?"));
            given(questionService.getAllQuestions()).willReturn(questions);
            ResponseEntity entity = questionController.getAllQuestions();
            List<Question> response = (List<Question>) entity.getBody();
            assertTrue(response.size() == 3);
        }
        @Test
        void testAddNewQuestion() throws Exception {
            Quiz quiz = new Quiz();
        given(quizService.findById(any(long.class))).willReturn(quiz);
        given(questionService.addQuestion(any(Question.class), any(Quiz.class))).willReturn("Saved");
        given(answerService.addAnswer(any(Answer.class), any(Question.class))).willReturn("Succesvol");
        Question question = new Question();
            List<Answer> answers = new ArrayList<>();
            answers.add(new Answer(1L, "pallet town", true, new Question()));
            answers.add(new Answer(1L, "to stop making people do drugs", false, new Question()));
            answers.add(new Answer(1L, "lazy town", false, new Question()));
        question.setAnswers(answers);
        String expectedResponse = "Saved";
        String response = questionController.addNewQuestion(question, 1L);
        assertTrue(response.equals(expectedResponse));
        }
        @Test
        void testUpdateQuestion(){
            Question question = new Question();
            given(questionService.updateQuestion(any(Question.class))).willReturn("Succesvol");
            ResponseEntity entity = questionController.updateQuiz(question);
            String expectedResponse = "Succesvol";
            assertTrue(entity.getBody().equals(expectedResponse));
        }
        @Test
        void testGetCurrentQuestion() throws Exception {
            Question question = new Question(4L, new ArrayList<>(), new Quiz(), "how old is ash ketchum from pallet town?");
            given(questionService.getCurrentQuestion(any(long.class), any(int.class))).willReturn(question);
            ResponseEntity entity = questionController.getCurrentQuestion(4L, 1);
            Question response = (Question) entity.getBody();
            assertTrue(response.getQuestionName().equals("how old is ash ketchum from pallet town?"));
        }
        @Test
        void testGetByCode(){
            Quiz quiz = new Quiz();
            quiz.setQuizName("testQuiz");
            given(quizService.findByCode(any(String.class))).willReturn(quiz);
            ResponseEntity entity = quizController.getByCode("123");
            Quiz responsequiz = (Quiz) entity.getBody();
            assertTrue(responsequiz.getQuizName().equals("testQuiz"));
        }
        @Test
        void testAddNewQuiz() throws Exception {
            User user = new User();
            given(userService.findById(any(long.class))).willReturn(user);
            given(quizService.addQuiz(any(Quiz.class), any(User.class))).willReturn("Succesvol");
            List<Question> questions = new ArrayList<>();
            questions.add(new Question(1L, new ArrayList<>(), new Quiz(), "what is giorno giovanna's dream?"));
            questions.add(new Question(2L, new ArrayList<>(), new Quiz(), "how old is ash ketchum from pallet town?"));
            questions.add(new Question(3L, new ArrayList<>(), new Quiz(), "is that a jojo reference?"));
            Quiz quiz = new Quiz();
            quiz.setQuestions(questions);
            String response = quizController.addNewQuiz(quiz, 1L);
            assertTrue(response.equals("Saved"));
        }
        @Test
        void testGetPersonalQuizzes(){
            List<Quiz> quizzes = new ArrayList<>();
            quizzes.add(new Quiz(1L, "the weeb quiz", new ArrayList<>(), new User(), "S3NDB0BSANDV4G3N3", new ArrayList<>()));
            quizzes.add(new Quiz(2L, "potato race quiz", new ArrayList<>(), new User(),"1", new ArrayList<>()));
            quizzes.add(new Quiz(3L, "memes on a stick", new ArrayList<>(), new User(),"1", new ArrayList<>()));
            given(quizService.getQuizzesByUserID(any(long.class))).willReturn(quizzes);
            List<QuizREST> returnquizzes = quizController.getPersonalQuizzes(1L);
            assertTrue(returnquizzes.size() == 3);
        }
        @Test
        void testUpdateQuiz(){
            given(quizService.updateQuiz(any(Quiz.class))).willReturn("Succesvol");
            Quiz quiz = new Quiz();
            ResponseEntity entity = quizController.updateQuiz(quiz);
            String response = (String) entity.getBody();
            assertTrue(response.equals("Succesvol"));
        }
        /*@Test
        void testSocketGetAllQuiz() throws JsonProcessingException {
            List<Quiz> quizzes = new ArrayList<>();
            quizzes.add(new Quiz(1L, "the weeb quiz", new ArrayList<>(), new User(), "S3NDB0BSANDV4G3N3", new ArrayList<>()));
            quizzes.add(new Quiz(2L, "potato race quiz", new ArrayList<>(), new User(),"1", new ArrayList<>()));
            quizzes.add(new Quiz(3L, "memes on a stick", new ArrayList<>(), new User(),"1", new ArrayList<>()));
        given(quizService.getAllQuiz()).willReturn(quizzes);
        ResponseEntity entity = socketController.getAllQuiz();
        String list = (String) entity.getBody();
        List<Quiz> response = mapper.readValue(list, new TypeReference<List<Quiz>>(){});
        assertTrue(response.size() == 3);
        }*/
    /*    @Test
        void testSocketLeaveGame(){

        }
        @Test
        void testSocketJoinGame(){

        }
        @Test
        void testSocketStartGame(){

        }*/
    }

