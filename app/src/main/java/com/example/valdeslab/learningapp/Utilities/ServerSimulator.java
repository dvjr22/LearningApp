package com.example.valdeslab.learningapp.Utilities;

import java.util.ArrayList;

/*****************************************************************************************************************************
* Class to simulate how server will work...sort of
*/
public class ServerSimulator {

	// List to store data "from the server"
	private static ArrayList<Integer> id;
	private static ArrayList<String> question;
	
	// Variables to simulate data "from the server"
//	private static final int SSX_COUNT = 79;
	private static final int SSX_COUNT = 72;
	private static final int QUES_COUNT = 12;
	private static final String SSX_STRING = "SSX in category ";
	private static final String QUES_STRING = "Question ";

	// track how often "the server" was queried
	private static int count = 0;

	// set up fake data
	static {

		id = new ArrayList<>();
		question = new ArrayList<>();

/*
		for (int i = 0; i < SSX_COUNT; i++) {
			int category = (i % 7) + 1;
			id.add(category);
			question.add(SSX_STRING + " " + category);
		}
*/

		for (int i = 0; i < 7; i++) {
			int category = i + 1;
			id.add(category);
			question.add(SSX_STRING + " " + category);

		}

		for (int i = 0; i < SSX_COUNT; i++) {
			int category = random(1, 7);
			id.add(category);
			question.add(SSX_STRING + " " + category);

		}

		for (int i = 0; i < QUES_COUNT; i++) {
			id.add(i + 10);
			question.add(QUES_STRING + Integer.toString(i + 1));
		}
	}

	/*********************************************************************************************************************
	* Called to request simulated server data
	*/
	public static ArrayList<ArrayList> request() {
		
		count++;
		return (count != 22) ? request(count) : request(0);
	}


	/*********************************************************************************************************************
	* Simulates data from server
	*/
	private static ArrayList<ArrayList> request(int number) {

		// Lists for storage
		ArrayList<Integer> server_id = new ArrayList<>();
		ArrayList<String> server_question = new ArrayList<>();
		ArrayList<ArrayList> server_lists = new ArrayList<>();

		// Handle ids not in range
		if (number == 8 || number == 9) number  = number/2;

		// Handle 0, used to end kiosk
		if (number == 0) {
			server_id.add(0);
			server_question.add("Finish");
			server_lists.add(server_id);
			server_lists.add(server_question);
			count = 0;
			return server_lists;
		}

		// Populate lists with data to be displayed
		for (int i = 0; i < id.size(); i++) {
			if (number == id.get(i)) {
				server_id.add(id.get(i));
				server_question.add(question.get(i));
			}
		}

		// place data lists in list
		server_lists.add(server_id);
		server_lists.add(server_question);

		return server_lists;
	}


	public static int random(int min, int max) {

		return min + (int)(Math.random() * ((max - min) + 1));
	}

/*****************************************************************************************************************************
* For testing purposes begin
*/
	public static void main(String args[]) {

		System.out.println("test 1 ");

		int test = 0;

		while (test < 22) {

			ArrayList<ArrayList> server_response = ServerSimulator.request();
			for (int i = 0; i < server_response.size(); i++) {
				System.out.println(server_response.get(i));
			}
			test++;
			System.out.println();

		}

		System.out.println("test 2 ");

		test = 0;

		while (test < 22) {

			ArrayList<ArrayList> server_response = ServerSimulator.request();
			for (int i = 0; i < server_response.size(); i++) {
				System.out.println(server_response.get(i));
			}
			test++;
			System.out.println();

		}
	}
/*
* For testing purposes end
*****************************************************************************************************************************/

}



