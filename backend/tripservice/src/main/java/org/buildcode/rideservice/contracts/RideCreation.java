package org.buildcode.rideservice.contracts;

import io.reactivex.Flowable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple10;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

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
    public static final String BINARY = "0x608060405234801561001057600080fd5b506114ac806100206000396000f3fe608060405260043610610051576000357c010000000000000000000000000000000000000000000000000000000090048063551adce214610056578063647722b61461009c578063ec15914d146100c5575b600080fd5b34801561006257600080fd5b5061007d60048036036100789190810190610d4e565b6100ee565b6040516100939a9998979695949392919061106d565b60405180910390f35b3480156100a857600080fd5b506100c360048036036100be9190810190610de3565b610560565b005b3480156100d157600080fd5b506100ec60048036036100e79190810190610d8f565b61090c565b005b60608060608060008060606000806000808b6040516020018082805190602001908083835b6020831015156101385780518252602082019150602081019050602083039250610113565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051602081830303815290604052805190602001209050600080600083815260200190815260200160002090506000816000018054600181600116156101000203166002900490501115156101e8576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101df9061127d565b60405180910390fd5b8060000181600101826002016000018360020160010184600201600201548560020160030154866002016004018760070160009054906101000a900460ff1688600801548960090154898054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156102c65780601f1061029b576101008083540402835291602001916102c6565b820191906000526020600020905b8154815290600101906020018083116102a957829003601f168201915b50505050509950888054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103625780601f1061033757610100808354040283529160200191610362565b820191906000526020600020905b81548152906001019060200180831161034557829003601f168201915b50505050509850878054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103fe5780601f106103d3576101008083540402835291602001916103fe565b820191906000526020600020905b8154815290600101906020018083116103e157829003601f168201915b50505050509750868054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561049a5780601f1061046f5761010080835404028352916020019161049a565b820191906000526020600020905b81548152906001019060200180831161047d57829003601f168201915b50505050509650838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105365780601f1061050b57610100808354040283529160200191610536565b820191906000526020600020905b81548152906001019060200180831161051957829003601f168201915b505050505093509b509b509b509b509b509b509b509b509b509b5050509193959799509193959799565b60008160000151511115156105aa576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105a19061129d565b60405180910390fd5b60008160a001511115156105f3576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016105ea906112bd565b60405180910390fd5b600081602001515111151561063d576040517f08c379a0000000000000000000000000000000000000000000000000000000008152600401610634906112fd565b60405180910390fd5b600081600001516040516020018082805190602001908083835b60208310151561067c5780518252602082019150602081019050602083039250610657565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040516020818303038152906040528051906020012090506000806000838152602001908152602001600020600001805460018160011615610100020316600290049050141515610727576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161071e906112dd565b60405180910390fd5b60c060405190810160405280836000015181526020018360200151815260200160a0604051908101604052808560400151815260200185606001518152602001856080015181526020018560a0015181526020018560c0015181525081526020016000600281111561079557fe5b81526020014281526020014281525060008083815260200190815260200160002060008201518160000190805190602001906107d2929190610aad565b5060208201518160010190805190602001906107ef929190610aad565b506040820151816002016000820151816000019080519060200190610815929190610aad565b506020820151816001019080519060200190610832929190610aad565b5060408201518160020155606082015181600301556080820151816004019080519060200190610863929190610aad565b50505060608201518160070160006101000a81548160ff0219169083600281111561088a57fe5b02179055506080820151816008015560a082015181600901559050507f567689d248ce69508d52172770e8496be13dc1f1b7c0a587a04e868511743977826000015183602001518460400151856060015186608001518760a001518860c00151600060405161090098979695949392919061112c565b60405180910390a15050565b6000826040516020018082805190602001908083835b6020831015156109475780518252602082019150602081019050602083039250610922565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051602081830303815290604052805190602001209050600080600083815260200190815260200160002090506000816000018054600181600116156101000203166002900490501115156109f7576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016109ee9061127d565b60405180910390fd5b828160070160006101000a81548160ff02191690836002811115610a1757fe5b02179055504281600901819055507fbaaa58dd666675a598183037dbd1406522603a241d4902a9a22cb65df91ccdb18160000182600101836002016000018460020160010185600201600201548660020160030154876002016004018860070160009054906101000a900460ff168960090154604051610a9f999897969594939291906111cd565b60405180910390a150505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610aee57805160ff1916838001178555610b1c565b82800160010185558215610b1c579182015b82811115610b1b578251825591602001919060010190610b00565b5b509050610b299190610b2d565b5090565b610b4f91905b80821115610b4b576000816000905550600101610b33565b5090565b90565b6000610b5e82356113ea565b905092915050565b600082601f8301121515610b7957600080fd5b8135610b8c610b878261134a565b61131d565b91508082526020830160208301858383011115610ba857600080fd5b610bb383828461141f565b50505092915050565b600082601f8301121515610bcf57600080fd5b8135610be2610bdd82611376565b61131d565b91508082526020830160208301858383011115610bfe57600080fd5b610c0983828461141f565b50505092915050565b600060e08284031215610c2457600080fd5b610c2e60e061131d565b9050600082013567ffffffffffffffff811115610c4a57600080fd5b610c5684828501610b66565b600083015250602082013567ffffffffffffffff811115610c7657600080fd5b610c8284828501610b66565b602083015250604082013567ffffffffffffffff811115610ca257600080fd5b610cae84828501610b66565b604083015250606082013567ffffffffffffffff811115610cce57600080fd5b610cda84828501610b66565b6060830152506080610cee84828501610d3a565b60808301525060a0610d0284828501610d3a565b60a08301525060c082013567ffffffffffffffff811115610d2257600080fd5b610d2e84828501610b66565b60c08301525092915050565b6000610d468235611403565b905092915050565b600060208284031215610d6057600080fd5b600082013567ffffffffffffffff811115610d7a57600080fd5b610d8684828501610bbc565b91505092915050565b60008060408385031215610da257600080fd5b600083013567ffffffffffffffff811115610dbc57600080fd5b610dc885828601610bbc565b9250506020610dd985828601610b52565b9150509250929050565b600060208284031215610df557600080fd5b600082013567ffffffffffffffff811115610e0f57600080fd5b610e1b84828501610c12565b91505092915050565b610e2d8161140d565b82525050565b6000610e3e826113bf565b808452610e5281602086016020860161142e565b610e5b81611461565b602085010191505092915050565b6000610e74826113b4565b808452610e8881602086016020860161142e565b610e9181611461565b602085010191505092915050565b600081546001811660008114610ebc5760018114610edc57610f1d565b607f600283041680865260ff198316602087015260408601935050610f1d565b60028204808652602086019550610ef2856113a2565b60005b82811015610f1457815481890152600182019150602081019050610ef5565b80880195505050505b505092915050565b6000601382527f5269646520646f6573206e6f74206578697374000000000000000000000000006020830152604082019050919050565b6000601782527f526964652049442063616e6e6f7420626520656d7074790000000000000000006020830152604082019050919050565b6000602982527f5468657265206d757374206265206174206c65617374206f6e6520617661696c60208301527f61626c65207365617400000000000000000000000000000000000000000000006040830152606082019050919050565b6000601682527f5269646520494420616c726561647920657869737473000000000000000000006020830152604082019050919050565b6000601782527f557365722049442063616e6e6f7420626520656d7074790000000000000000006020830152604082019050919050565b611067816113e0565b82525050565b6000610140820190508181036000830152611088818d610e33565b9050818103602083015261109c818c610e33565b905081810360408301526110b0818b610e33565b905081810360608301526110c4818a610e33565b90506110d3608083018961105e565b6110e060a083018861105e565b81810360c08301526110f28187610e33565b905061110160e0830186610e24565b61110f61010083018561105e565b61111d61012083018461105e565b9b9a5050505050505050505050565b6000610100820190508181036000830152611147818b610e69565b9050818103602083015261115b818a610e69565b9050818103604083015261116f8189610e69565b905081810360608301526111838188610e69565b9050611192608083018761105e565b61119f60a083018661105e565b81810360c08301526111b18185610e69565b90506111c060e0830184610e24565b9998505050505050505050565b60006101208201905081810360008301526111e8818c610e9f565b905081810360208301526111fc818b610e9f565b90508181036040830152611210818a610e9f565b905081810360608301526112248189610e9f565b9050611233608083018861105e565b61124060a083018761105e565b81810360c08301526112528186610e9f565b905061126160e0830185610e24565b61126f61010083018461105e565b9a9950505050505050505050565b6000602082019050818103600083015261129681610f25565b9050919050565b600060208201905081810360008301526112b681610f5c565b9050919050565b600060208201905081810360008301526112d681610f93565b9050919050565b600060208201905081810360008301526112f681610ff0565b9050919050565b6000602082019050818103600083015261131681611027565b9050919050565b6000604051905081810181811067ffffffffffffffff8211171561134057600080fd5b8060405250919050565b600067ffffffffffffffff82111561136157600080fd5b601f19601f8301169050602081019050919050565b600067ffffffffffffffff82111561138d57600080fd5b601f19601f8301169050602081019050919050565b60008160005260206000209050919050565b600081519050919050565b600081519050919050565b60006003821015156113d857fe5b819050919050565b6000819050919050565b60006003821015156113fb57600080fd5b819050919050565b6000819050919050565b6000611418826113ca565b9050919050565b82818337600083830152505050565b60005b8381101561144c578082015181840152602081019050611431565b8381111561145b576000848401525b50505050565b6000601f19601f830116905091905056fea265627a7a723058204acc6850570a3ac2823c8010dfb725d5e886f3880b8b16f918cd24f1d97256146c6578706572696d656e74616cf50037";

    private static String librariesLinkedBinary;

    public static final String FUNC_CREATERIDE = "createRide";

    public static final String FUNC_GETRIDE = "getRide";

    public static final String FUNC_UPDATERIDESTATUS = "updateRideStatus";

    public static final Event RIDECREATED_EVENT = new Event("RideCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}));
    ;

    public static final Event RIDEUPDATED_EVENT = new Event("RideUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}));
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
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RIDECREATED_EVENT, transactionReceipt);
        ArrayList<RideCreatedEventResponse> responses = new ArrayList<RideCreatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RideCreatedEventResponse typedResponse = new RideCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.rideId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.userId = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.source = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.destination = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.fare = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.availableSeats = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.carModel = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RideCreatedEventResponse getRideCreatedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RIDECREATED_EVENT, log);
        RideCreatedEventResponse typedResponse = new RideCreatedEventResponse();
        typedResponse.log = log;
        typedResponse.rideId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.userId = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.source = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.destination = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.fare = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.availableSeats = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
        typedResponse.carModel = (String) eventValues.getNonIndexedValues().get(6).getValue();
        typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
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

    public static List<RideUpdatedEventResponse> getRideUpdatedEvents(
            TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = staticExtractEventParametersWithLog(RIDEUPDATED_EVENT, transactionReceipt);
        ArrayList<RideUpdatedEventResponse> responses = new ArrayList<RideUpdatedEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RideUpdatedEventResponse typedResponse = new RideUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.rideId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.userId = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.source = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse.destination = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse.fare = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse.availableSeats = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse.carModel = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
            typedResponse.updatedAt = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static RideUpdatedEventResponse getRideUpdatedEventFromLog(Log log) {
        EventValuesWithLog eventValues = staticExtractEventParametersWithLog(RIDEUPDATED_EVENT, log);
        RideUpdatedEventResponse typedResponse = new RideUpdatedEventResponse();
        typedResponse.log = log;
        typedResponse.rideId = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.userId = (String) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.source = (String) eventValues.getNonIndexedValues().get(2).getValue();
        typedResponse.destination = (String) eventValues.getNonIndexedValues().get(3).getValue();
        typedResponse.fare = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
        typedResponse.availableSeats = (BigInteger) eventValues.getNonIndexedValues().get(5).getValue();
        typedResponse.carModel = (String) eventValues.getNonIndexedValues().get(6).getValue();
        typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(7).getValue();
        typedResponse.updatedAt = (BigInteger) eventValues.getNonIndexedValues().get(8).getValue();
        return typedResponse;
    }

    public Flowable<RideUpdatedEventResponse> rideUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getRideUpdatedEventFromLog(log));
    }

    public Flowable<RideUpdatedEventResponse> rideUpdatedEventFlowable(
            DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RIDEUPDATED_EVENT));
        return rideUpdatedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createRide(Struct0 rideInput) {
        final Function function = new Function(
                FUNC_CREATERIDE, 
                Arrays.<Type>asList(rideInput), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple10<String, String, String, String, BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger>> getRide(
            String rideId) {
        final Function function = new Function(FUNC_GETRIDE, 
                Arrays.<Type>asList(new Utf8String(rideId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint8>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple10<String, String, String, String, BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple10<String, String, String, String, BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple10<String, String, String, String, BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger> call(
                            ) throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple10<String, String, String, String, BigInteger, BigInteger, String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (String) results.get(2).getValue(), 
                                (String) results.get(3).getValue(), 
                                (BigInteger) results.get(4).getValue(), 
                                (BigInteger) results.get(5).getValue(), 
                                (String) results.get(6).getValue(), 
                                (BigInteger) results.get(7).getValue(), 
                                (BigInteger) results.get(8).getValue(), 
                                (BigInteger) results.get(9).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> updateRideStatus(String rideId,
            BigInteger status) {
        final Function function = new Function(
                FUNC_UPDATERIDESTATUS, 
                Arrays.<Type>asList(new Utf8String(rideId),
                new Uint8(status)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
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

    public static class Struct0 extends DynamicStruct {
        public String rideId;

        public String userId;

        public String source;

        public String destination;

        public BigInteger fare;

        public BigInteger availableSeats;

        public String carModel;

        public Struct0(String rideId, String userId, String source, String destination,
                BigInteger fare, BigInteger availableSeats, String carModel) {
            super(new Utf8String(rideId),
                    new Utf8String(userId),
                    new Utf8String(source),
                    new Utf8String(destination),
                    new Uint256(fare),
                    new Uint256(availableSeats),
                    new Utf8String(carModel));
            this.rideId = rideId;
            this.userId = userId;
            this.source = source;
            this.destination = destination;
            this.fare = fare;
            this.availableSeats = availableSeats;
            this.carModel = carModel;
        }

        public Struct0(Utf8String rideId, Utf8String userId, Utf8String source,
                Utf8String destination, Uint256 fare, Uint256 availableSeats, Utf8String carModel) {
            super(rideId, userId, source, destination, fare, availableSeats, carModel);
            this.rideId = rideId.getValue();
            this.userId = userId.getValue();
            this.source = source.getValue();
            this.destination = destination.getValue();
            this.fare = fare.getValue();
            this.availableSeats = availableSeats.getValue();
            this.carModel = carModel.getValue();
        }
    }

    public static class RideCreatedEventResponse extends BaseEventResponse {
        public String rideId;

        public String userId;

        public String source;

        public String destination;

        public BigInteger fare;

        public BigInteger availableSeats;

        public String carModel;

        public BigInteger status;
    }

    public static class RideUpdatedEventResponse extends BaseEventResponse {
        public String rideId;

        public String userId;

        public String source;

        public String destination;

        public BigInteger fare;

        public BigInteger availableSeats;

        public String carModel;

        public BigInteger status;

        public BigInteger updatedAt;
    }
}
