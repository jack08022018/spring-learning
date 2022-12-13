package com.graphql.configuration;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(@NotNull Throwable e, @NotNull DataFetchingEnvironment env) {
        log.error("Exception while handling request: " + e.getMessage(), e);
        if(e instanceof ConstraintViolationException) {
            return handleConstraintViolationException((ConstraintViolationException)e, env);
        }
        return GraphqlErrorBuilder.newError(env)
                .errorType(ErrorType.INTERNAL_ERROR)
                .message(e.getMessage())
                .build();
    }

    private GraphQLError handleConstraintViolationException(ConstraintViolationException e, DataFetchingEnvironment env) {
        List<String> messages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return GraphqlErrorBuilder.newError(env)
                .errorType(ErrorType.BAD_REQUEST)
                .message(messages.toString())
                .build();
    }

    //    @Override
//    protected List<GraphQLError> resolveToMultipleErrors(Throwable e, DataFetchingEnvironment env) {
//        log.error("xxx resolveToMultipleErrors: " + e.getMessage(), e);
//        GraphQLError build = GraphqlErrorBuilder.newError(env)
//                .message(e.getMessage())
//                .build();
//        return Arrays.asList(build);
//    }

//    private GraphQLError toGraphQLError(Throwable e, DataFetchingEnvironment env) {
//        return GraphqlErrorBuilder.newError()
//                .errorType(ErrorType.INTERNAL_ERROR)
//                .message(e.getMessage())
//                .path(env.getExecutionStepInfo().getPath())
////              .location(env.getField().getSourceLocation())
//                .build();
//    }
//
}
