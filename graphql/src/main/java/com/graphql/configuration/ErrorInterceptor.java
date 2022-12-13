//package com.graphql.configuration.exceptionHandler;
//
//import graphql.GraphQLError;
//import graphql.GraphqlErrorBuilder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.graphql.server.WebGraphQlInterceptor;
//import org.springframework.graphql.server.WebGraphQlRequest;
//import org.springframework.graphql.server.WebGraphQlResponse;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Component
//public class ErrorInterceptor implements WebGraphQlInterceptor {
//
//    @Override
//    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
//        return chain.next(request).map(response -> {
//            if (response.isValid()) {
//                return response;
//            }
//            log.error("ErrorInterceptor: " + response.getErrors());
//            List<GraphQLError> errors = response.getErrors().stream()
//                    .map(error -> GraphqlErrorBuilder.newError()
//                        .errorType(error.getErrorType())
//                        .message(error.getMessage())
//                        .path(Collections.singletonList(request.getUri().getPath()))
//                        .build()
//                    )
//                    .collect(Collectors.toList());
//            return response.transform(builder -> builder.errors(errors).build());
//        });
//    }
//
//}
