package com.trellorest;

import org.apache.http.HttpStatus;
import org.junit.Test;

import java.util.List;

public class BoardTest extends BaseTest {

    @Test
    public void iPerformCreateBoardPostRequest() {
        String boardName = "New Board";

        request()
                .queryParam("name", boardName)
                .when()
                .post("boards/")
                .then()
                .statusCode(HttpStatus.SC_OK);

        _logger.info(boardName + " isimli yeni bir board oluşturuldu.");
    }

    @Test
    public void iPerformGetBoardRequest() {
        String randomBoardId = getRandomValue(getAllBoardIds());

        String boardName = request()
                .pathParam("boardId", randomBoardId)
                .get("boards/{boardId}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getString("name");

        _logger.info(randomBoardId + " id'li board getirildi.=> Board ismi: " + boardName);
    }

    @Test
    public void iPerformGetUsersBoards() {
        String userName = "huseyincamci1";

        String userAllBoards = request()
                .pathParam("userName", userName)
                .when()
                .get("members/{userName}/boards")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getString("name");

        _logger.info(userName + "'ya/ye ait tüm boardlar: " + userAllBoards);
    }

    @Test
    public void deleteBoardRequest() {
        String rndBoardId = getRandomValue(getAllBoardIds());

        request()
                .pathParam("boardId", rndBoardId)
                .when()
                .delete("boards/{boardId}")
                .then()
                .statusCode(HttpStatus.SC_OK);

        _logger.info(rndBoardId + " id'li board silindi.");
    }


    private List<String> getAllBoardIds() {

        return request()
                .pathParam("userName", "huseyincamci1")
                .when()
                .get("members/{userName}/boards")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .jsonPath().getList("id");
    }
}
