package com.taskagile.web.result;

import com.taskagile.domain.model.cardlist.CardList;
import com.taskagile.web.result.ApiResult;
import com.taskagile.web.result.Result;
import org.springframework.http.ResponseEntity;

public class AddCardListResult {
    public static ResponseEntity<ApiResult> build(CardList cardList) {
        ApiResult apiResult = ApiResult.blank()
                .add("id", cardList.getId().value())
                .add("name", cardList.getName());
        return Result.ok(apiResult);
    }
}
