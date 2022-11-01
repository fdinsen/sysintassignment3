package com.bookstore.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: Book.proto")
public final class bookStoreGrpc {

  private bookStoreGrpc() {}

  public static final String SERVICE_NAME = "bookStore";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.bookstore.grpc.Book.BuyBookRequest,
      com.bookstore.grpc.Book.APIResponse> getBuyBookMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "buyBook",
      requestType = com.bookstore.grpc.Book.BuyBookRequest.class,
      responseType = com.bookstore.grpc.Book.APIResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.bookstore.grpc.Book.BuyBookRequest,
      com.bookstore.grpc.Book.APIResponse> getBuyBookMethod() {
    io.grpc.MethodDescriptor<com.bookstore.grpc.Book.BuyBookRequest, com.bookstore.grpc.Book.APIResponse> getBuyBookMethod;
    if ((getBuyBookMethod = bookStoreGrpc.getBuyBookMethod) == null) {
      synchronized (bookStoreGrpc.class) {
        if ((getBuyBookMethod = bookStoreGrpc.getBuyBookMethod) == null) {
          bookStoreGrpc.getBuyBookMethod = getBuyBookMethod = 
              io.grpc.MethodDescriptor.<com.bookstore.grpc.Book.BuyBookRequest, com.bookstore.grpc.Book.APIResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "bookStore", "buyBook"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.bookstore.grpc.Book.BuyBookRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.bookstore.grpc.Book.APIResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new bookStoreMethodDescriptorSupplier("buyBook"))
                  .build();
          }
        }
     }
     return getBuyBookMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static bookStoreStub newStub(io.grpc.Channel channel) {
    return new bookStoreStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static bookStoreBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new bookStoreBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static bookStoreFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new bookStoreFutureStub(channel);
  }

  /**
   */
  public static abstract class bookStoreImplBase implements io.grpc.BindableService {

    /**
     */
    public void buyBook(com.bookstore.grpc.Book.BuyBookRequest request,
        io.grpc.stub.StreamObserver<com.bookstore.grpc.Book.APIResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getBuyBookMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBuyBookMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.bookstore.grpc.Book.BuyBookRequest,
                com.bookstore.grpc.Book.APIResponse>(
                  this, METHODID_BUY_BOOK)))
          .build();
    }
  }

  /**
   */
  public static final class bookStoreStub extends io.grpc.stub.AbstractStub<bookStoreStub> {
    private bookStoreStub(io.grpc.Channel channel) {
      super(channel);
    }

    private bookStoreStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected bookStoreStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new bookStoreStub(channel, callOptions);
    }

    /**
     */
    public void buyBook(com.bookstore.grpc.Book.BuyBookRequest request,
        io.grpc.stub.StreamObserver<com.bookstore.grpc.Book.APIResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBuyBookMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class bookStoreBlockingStub extends io.grpc.stub.AbstractStub<bookStoreBlockingStub> {
    private bookStoreBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private bookStoreBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected bookStoreBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new bookStoreBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.bookstore.grpc.Book.APIResponse buyBook(com.bookstore.grpc.Book.BuyBookRequest request) {
      return blockingUnaryCall(
          getChannel(), getBuyBookMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class bookStoreFutureStub extends io.grpc.stub.AbstractStub<bookStoreFutureStub> {
    private bookStoreFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private bookStoreFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected bookStoreFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new bookStoreFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.bookstore.grpc.Book.APIResponse> buyBook(
        com.bookstore.grpc.Book.BuyBookRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getBuyBookMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BUY_BOOK = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final bookStoreImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(bookStoreImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BUY_BOOK:
          serviceImpl.buyBook((com.bookstore.grpc.Book.BuyBookRequest) request,
              (io.grpc.stub.StreamObserver<com.bookstore.grpc.Book.APIResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class bookStoreBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    bookStoreBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.bookstore.grpc.Book.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("bookStore");
    }
  }

  private static final class bookStoreFileDescriptorSupplier
      extends bookStoreBaseDescriptorSupplier {
    bookStoreFileDescriptorSupplier() {}
  }

  private static final class bookStoreMethodDescriptorSupplier
      extends bookStoreBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    bookStoreMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (bookStoreGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new bookStoreFileDescriptorSupplier())
              .addMethod(getBuyBookMethod())
              .build();
        }
      }
    }
    return result;
  }
}
