package org.buildcode.tripservice.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.1.
 */
@SuppressWarnings("rawtypes")
public class RideCreation extends Contract {
    public static final String BINARY = "0x60806040525f8055348015610012575f80fd5b50610f9e806100205f395ff3fe608060405234801561000f575f80fd5b506004361061004a575f3560e01c80635205f1121461004e5780637c7b0ff414610084578063b10fefc0146100b4578063f5e6a7fb146100d2575b5f80fd5b61006860048036038101906100639190610682565b6100ee565b60405161007b979695949392919061079f565b60405180910390f35b61009e60048036038101906100999190610682565b610263565b6040516100ab919061092f565b60405180910390f35b6100bc61043a565b6040516100c9919061094f565b60405180910390f35b6100ec60048036038101906100e79190610a94565b61043f565b005b6001602052805f5260405f205f91509050805f015490806001015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff169080600201805461013890610b5d565b80601f016020809104026020016040519081016040528092919081815260200182805461016490610b5d565b80156101af5780601f10610186576101008083540402835291602001916101af565b820191905f5260205f20905b81548152906001019060200180831161019257829003601f168201915b5050505050908060030180546101c490610b5d565b80601f01602080910402602001604051908101604052809291908181526020018280546101f090610b5d565b801561023b5780601f106102125761010080835404028352916020019161023b565b820191905f5260205f20905b81548152906001019060200180831161021e57829003601f168201915b505050505090806004015490806005015490806006015f9054906101000a900460ff16905087565b61026b6105ee565b60015f8381526020019081526020015f206040518060e00160405290815f8201548152602001600182015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016002820180546102f590610b5d565b80601f016020809104026020016040519081016040528092919081815260200182805461032190610b5d565b801561036c5780601f106103435761010080835404028352916020019161036c565b820191905f5260205f20905b81548152906001019060200180831161034f57829003601f168201915b5050505050815260200160038201805461038590610b5d565b80601f01602080910402602001604051908101604052809291908181526020018280546103b190610b5d565b80156103fc5780601f106103d3576101008083540402835291602001916103fc565b820191905f5260205f20905b8154815290600101906020018083116103df57829003601f168201915b505050505081526020016004820154815260200160058201548152602001600682015f9054906101000a900460ff1615151515815250509050919050565b5f5481565b5f8111610481576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161047890610bfd565b60405180910390fd5b5f8081548092919061049290610c48565b91905055506040518060e001604052805f5481526020013373ffffffffffffffffffffffffffffffffffffffff1681526020018581526020018481526020018381526020018281526020016001151581525060015f805481526020019081526020015f205f820151815f01556020820151816001015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060408201518160020190816105599190610e2c565b50606082015181600301908161056f9190610e2c565b506080820151816004015560a0820151816005015560c0820151816006015f6101000a81548160ff0219169083151502179055509050507f36e6d42a02073586548e9846366b6279a320a05df477f8cbf00d8b4b2a0dd4765f5433868686866040516105e096959493929190610efb565b60405180910390a150505050565b6040518060e001604052805f81526020015f73ffffffffffffffffffffffffffffffffffffffff16815260200160608152602001606081526020015f81526020015f81526020015f151581525090565b5f604051905090565b5f80fd5b5f80fd5b5f819050919050565b6106618161064f565b811461066b575f80fd5b50565b5f8135905061067c81610658565b92915050565b5f6020828403121561069757610696610647565b5b5f6106a48482850161066e565b91505092915050565b6106b68161064f565b82525050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f6106e5826106bc565b9050919050565b6106f5816106db565b82525050565b5f81519050919050565b5f82825260208201905092915050565b5f5b83811015610732578082015181840152602081019050610717565b5f8484015250505050565b5f601f19601f8301169050919050565b5f610757826106fb565b6107618185610705565b9350610771818560208601610715565b61077a8161073d565b840191505092915050565b5f8115159050919050565b61079981610785565b82525050565b5f60e0820190506107b25f83018a6106ad565b6107bf60208301896106ec565b81810360408301526107d1818861074d565b905081810360608301526107e5818761074d565b90506107f460808301866106ad565b61080160a08301856106ad565b61080e60c0830184610790565b98975050505050505050565b6108238161064f565b82525050565b610832816106db565b82525050565b5f82825260208201905092915050565b5f610852826106fb565b61085c8185610838565b935061086c818560208601610715565b6108758161073d565b840191505092915050565b61088981610785565b82525050565b5f60e083015f8301516108a45f86018261081a565b5060208301516108b76020860182610829565b50604083015184820360408601526108cf8282610848565b915050606083015184820360608601526108e98282610848565b91505060808301516108fe608086018261081a565b5060a083015161091160a086018261081a565b5060c083015161092460c0860182610880565b508091505092915050565b5f6020820190508181035f830152610947818461088f565b905092915050565b5f6020820190506109625f8301846106ad565b92915050565b5f80fd5b5f80fd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b6109a68261073d565b810181811067ffffffffffffffff821117156109c5576109c4610970565b5b80604052505050565b5f6109d761063e565b90506109e3828261099d565b919050565b5f67ffffffffffffffff821115610a0257610a01610970565b5b610a0b8261073d565b9050602081019050919050565b828183375f83830152505050565b5f610a38610a33846109e8565b6109ce565b905082815260208101848484011115610a5457610a5361096c565b5b610a5f848285610a18565b509392505050565b5f82601f830112610a7b57610a7a610968565b5b8135610a8b848260208601610a26565b91505092915050565b5f805f8060808587031215610aac57610aab610647565b5b5f85013567ffffffffffffffff811115610ac957610ac861064b565b5b610ad587828801610a67565b945050602085013567ffffffffffffffff811115610af657610af561064b565b5b610b0287828801610a67565b9350506040610b138782880161066e565b9250506060610b248782880161066e565b91505092959194509250565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f6002820490506001821680610b7457607f821691505b602082108103610b8757610b86610b30565b5b50919050565b7f5468657265206d757374206265206174206c65617374206f6e6520617661696c5f8201527f61626c6520736561740000000000000000000000000000000000000000000000602082015250565b5f610be7602983610705565b9150610bf282610b8d565b604082019050919050565b5f6020820190508181035f830152610c1481610bdb565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52601160045260245ffd5b5f610c528261064f565b91507fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff8203610c8457610c83610c1b565b5b600182019050919050565b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f60088302610ceb7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff82610cb0565b610cf58683610cb0565b95508019841693508086168417925050509392505050565b5f819050919050565b5f610d30610d2b610d268461064f565b610d0d565b61064f565b9050919050565b5f819050919050565b610d4983610d16565b610d5d610d5582610d37565b848454610cbc565b825550505050565b5f90565b610d71610d65565b610d7c818484610d40565b505050565b5b81811015610d9f57610d945f82610d69565b600181019050610d82565b5050565b601f821115610de457610db581610c8f565b610dbe84610ca1565b81016020851015610dcd578190505b610de1610dd985610ca1565b830182610d81565b50505b505050565b5f82821c905092915050565b5f610e045f1984600802610de9565b1980831691505092915050565b5f610e1c8383610df5565b9150826002028217905092915050565b610e35826106fb565b67ffffffffffffffff811115610e4e57610e4d610970565b5b610e588254610b5d565b610e63828285610da3565b5f60209050601f831160018114610e94575f8415610e82578287015190505b610e8c8582610e11565b865550610ef3565b601f198416610ea286610c8f565b5f5b82811015610ec957848901518255600182019150602085019450602081019050610ea4565b86831015610ee65784890151610ee2601f891682610df5565b8355505b6001600288020188555050505b505050505050565b5f60c082019050610f0e5f8301896106ad565b610f1b60208301886106ec565b8181036040830152610f2d818761074d565b90508181036060830152610f41818661074d565b9050610f5060808301856106ad565b610f5d60a08301846106ad565b97965050505050505056fea2646970667358221220578f753971e746d3e7ed6c29c9ecfdb17893d1b0bed0527f7f806b438a4fdd4764736f6c63430008150033";

    private static String librariesLinkedBinary;

    public static final String FUNC_RIDECOUNTER = "rideCounter";

    public static final String FUNC_RIDES = "rides";

    public static final String FUNC_CREATERIDE = "createRide";

    public static final String FUNC_GETRIDE = "getRide";

    public static final Event RIDECREATED_EVENT = new Event("RideCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected RideCreation(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected RideCreation(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected RideCreation(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected RideCreation(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<RideCreatedEventResponse> getRideCreatedEvents(
            TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RIDECREATED_EVENT, transactionReceipt);
        ArrayList<RideCreatedEventResponse> responses = new ArrayList<RideCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RideCreatedEventResponse typedResponse = new RideCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.rideId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.driver = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.pickupLocation = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.dropoffLocation = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.fare = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.availableSeats = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RideCreatedEventResponse getRideCreatedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RIDECREATED_EVENT, log);
        RideCreatedEventResponse typedResponse = new RideCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.rideId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.driver = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.pickupLocation = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.dropoffLocation = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.fare = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.availableSeats = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
        return typedResponse;
    }

    public Flowable<RideCreatedEventResponse> rideCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRideCreatedEventFromLog(log));
    }

    public Flowable<RideCreatedEventResponse> rideCreatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RIDECREATED_EVENT));
        return rideCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> rideCounter() {
        final Function function = new Function(FUNC_RIDECOUNTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, Boolean>> rides(
            BigInteger param0) {
        final Function function = new Function(FUNC_RIDES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, Boolean>>(function,
                new Callable<Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, Boolean> call(
                            ) throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple7<BigInteger, String, String, String, BigInteger, BigInteger, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (Boolean) results.get(6).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> createRide(String pickupLocation,
            String dropoffLocation, BigInteger fare, BigInteger availableSeats) {
        final Function function = new Function(
                FUNC_CREATERIDE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(pickupLocation), 
                new org.web3j.abi.datatypes.Utf8String(dropoffLocation), 
                new org.web3j.abi.datatypes.generated.Uint256(fare), 
                new org.web3j.abi.datatypes.generated.Uint256(availableSeats)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Ride> getRide(BigInteger rideId) {
        final Function function = new Function(FUNC_GETRIDE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(rideId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Ride>() {}));
        return executeRemoteCallSingleValueReturn(function, Ride.class);
    }

    @Deprecated
    public static RideCreation load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new RideCreation(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static RideCreation load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new RideCreation(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RideCreation load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new RideCreation(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static RideCreation load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new RideCreation(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<RideCreation> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RideCreation.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<RideCreation> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RideCreation.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<RideCreation> deploy(Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(RideCreation.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<RideCreation> deploy(Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(RideCreation.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

    public static class Ride extends DynamicStruct {
        public BigInteger rideId;

        public String driver;

        public String pickupLocation;

        public String dropoffLocation;

        public BigInteger fare;

        public BigInteger availableSeats;

        public Boolean isActive;

        public Ride(BigInteger rideId, String driver, String pickupLocation, String dropoffLocation,
                BigInteger fare, BigInteger availableSeats, Boolean isActive) {
            super(new org.web3j.abi.datatypes.generated.Uint256(rideId), 
                    new org.web3j.abi.datatypes.Address(160, driver), 
                    new org.web3j.abi.datatypes.Utf8String(pickupLocation), 
                    new org.web3j.abi.datatypes.Utf8String(dropoffLocation), 
                    new org.web3j.abi.datatypes.generated.Uint256(fare), 
                    new org.web3j.abi.datatypes.generated.Uint256(availableSeats), 
                    new org.web3j.abi.datatypes.Bool(isActive));
            this.rideId = rideId;
            this.driver = driver;
            this.pickupLocation = pickupLocation;
            this.dropoffLocation = dropoffLocation;
            this.fare = fare;
            this.availableSeats = availableSeats;
            this.isActive = isActive;
        }

        public Ride(Uint256 rideId, Address driver, Utf8String pickupLocation,
                Utf8String dropoffLocation, Uint256 fare, Uint256 availableSeats, Bool isActive) {
            super(rideId, driver, pickupLocation, dropoffLocation, fare, availableSeats, isActive);
            this.rideId = rideId.getValue();
            this.driver = driver.getValue();
            this.pickupLocation = pickupLocation.getValue();
            this.dropoffLocation = dropoffLocation.getValue();
            this.fare = fare.getValue();
            this.availableSeats = availableSeats.getValue();
            this.isActive = isActive.getValue();
        }
    }

    public static class RideCreatedEventResponse extends BaseEventResponse {
        public BigInteger rideId;

        public String driver;

        public String pickupLocation;

        public String dropoffLocation;

        public BigInteger fare;

        public BigInteger availableSeats;
    }
}
