package com.example.quizly;

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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class QuizlyApplicationTests {

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
	private AuthController authController;

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
	void testRegister(){
		User user = new User();
		user.setName("potato-san");
		user.setEmail("potato@gmail.com");
		user.setPassword("iLovePotatoes420");
		//given(mockUserRepo.save(any(User.class))).willReturn(user);
		RegisterModel model = new RegisterModel("potato-san", "potato@gmail.com", "iLovePotatoes420", "iLovePotatoes420");
		String response = authService.register(model);
		String expected = "SuccesFull";
		assertTrue(response.equals(expected));
	}
	@Test
	void testLogin() {
		LoginModel model = new LoginModel();
		model.setEmail("futureWizardKinguh@gmail.com");
		model.setPassword("WillBenextWizardoOhkokhu");
		LoginResponse response = authService.login(model);
		String expectedName = "asta bruh";
		assertTrue(response.getName().equals(expectedName));
	}
	@Test
	void testUser(){
		User user = new User(3L, "asta bruh", "futureWizardKinguh@gmail.com", "WillBenextWizardoOhkokhu", new ArrayList<>());
		assertTrue(user.getName().equals("asta bruh"));
		assertTrue(user.getEmail().equals("futureWizardKinguh@gmail.com"));
		assertTrue(user.getUserId().equals(3L));
		assertTrue(user.getPassword().equals("WillBenextWizardoOhkokhu"));
	}

	@Test
	void testAnswer(){
		Answer answer = new Answer(1L, "pallet town", true, new Question());
		assertTrue(answer.getAnswerId().equals(1L));
		assertTrue(answer.getAnswerContent().equals("pallet town"));
		assertTrue(answer.isRightAnswer());
	}

	@Test
	void testGuest(){
		Guest guest = new Guest(2L, "guestTest", new Quiz());
		assertTrue(guest.getGuestId().equals(2L));
		assertTrue(guest.getName().equals("guestTest"));
	}
	@Test
	void testQuestion(){
		Question question = new Question(4L, new ArrayList<>(), new Quiz(), "how old is ash ketchum from pallet town?");
		assertTrue(question.getQuestionId() == 4L);
		assertTrue(question.getQuestionName().equals("how old is ash ketchum from pallet town?"));
	}
	@Test
	void testQuiz(){
		Quiz quiz = new Quiz(5L, "memes on a stick", new ArrayList<>(), new User(),"1", new ArrayList<>());
		assertTrue(quiz.getQuizId() == 5L);
		assertTrue(quiz.getQuizName().equals("memes on a stick"));
		assertTrue(quiz.getCode().equals("1"));
	}
	@Test
	void testGuestModel(){
		GuestModel model = new GuestModel();
		model.setName("testGuest");
		model.setCode("code");
		assertTrue(model.getName().equals("testGuest"));
		assertTrue(model.getCode().equals("code"));
	}
	@Test
	void testLoginModel(){
		LoginModel model = new LoginModel();
		model.setEmail("test.test@test.nl");
		model.setPassword("Password");
		assertTrue(model.getEmail().equals("test.test@test.nl"));
		assertTrue(model.getPassword().equals("Password"));
	}
	@Test
	void testRegisterModel(){
		RegisterModel model = new RegisterModel();
		model.setEmail("test.test@test.nl");
		model.setName("test");
		model.setPassword("Password");
		model.setSecondPassword("Password");
		assertTrue(model.getEmail().equals("test.test@test.nl"));
		assertTrue(model.getName().equals("test"));
		assertTrue(model.getPassword().equals("Password"));
		assertTrue(model.getSecondPassword().equals("Password"));
	}
	@Test
	void testLoginResponse(){
		LoginResponse response = new LoginResponse();
		response.setName("test");
		response.setUserId(1L);
		assertTrue(response.getName().equals("test"));
		assertTrue(response.getUserId() == 1L);
	}
	@Test
	void testQuizREST(){
		QuizREST rest = new QuizREST();
		rest.setCode("1");
		rest.setQuizId(2L);
		rest.setQuizName("testname");
		assertTrue(rest.getCode().equals("1"));
		assertTrue(rest.getQuizId() == 2L);
		assertTrue(rest.getQuizName().equals("testname"));
	}
}
