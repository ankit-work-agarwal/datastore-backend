package com.personal.datastore.service;

import com.personal.datastore.dto.BankAccountDTO;
import com.personal.datastore.dto.ContactDTO;
import com.personal.datastore.dto.DocumentDTO;
import com.personal.datastore.dto.FamilyMemberDTO;
import com.personal.datastore.dto.InsuranceDTO;
import com.personal.datastore.dto.InvestmentDTO;
import com.personal.datastore.dto.LoanDTO;
import com.personal.datastore.dto.MedicalRecordDTO;
import com.personal.datastore.dto.PropertyDTO;
import com.personal.datastore.dto.SubscriptionDTO;
import com.personal.datastore.dto.VehicleDTO;
import com.personal.datastore.model.BankAccount;
import com.personal.datastore.model.Contact;
import com.personal.datastore.model.Document;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.model.Insurance;
import com.personal.datastore.model.Investment;
import com.personal.datastore.model.Loan;
import com.personal.datastore.model.MedicalRecord;
import com.personal.datastore.model.Property;
import com.personal.datastore.model.Subscription;
import com.personal.datastore.model.Vehicle;
import com.personal.datastore.exception.ResourceNotFoundException;
import com.personal.datastore.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository repository;

    public FamilyMemberDTO addMember(FamilyMember member) {
        return mapToDTO(repository.save(member));
    }

    public List<FamilyMemberDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public FamilyMemberDTO getById(Long id) {
        FamilyMember member = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + id));
        return mapToDTO(member);
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + id));
        repository.deleteById(id);
    }

    public FamilyMemberDTO update(Long id, FamilyMember updated) {
        FamilyMember existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + id));
        existing.setName(updated.getName());
        existing.setRelation(updated.getRelation());
        existing.setPhone(updated.getPhone());
        existing.setEmail(updated.getEmail());
        return mapToDTO(repository.save(existing));
    }

    private FamilyMemberDTO mapToDTO(FamilyMember member) {

        FamilyMemberDTO dto = new FamilyMemberDTO();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setRelation(member.getRelation());
        dto.setPhone(member.getPhone());
        dto.setEmail(member.getEmail());

        if (member.getVehicles() != null) {
            List<VehicleDTO> vehicles = member.getVehicles()
                    .stream()
                    .map(this::mapVehicle)
                    .toList();
            dto.setVehicles(vehicles);
        }

        if (member.getDocuments() != null) {
            List<DocumentDTO> docs = member.getDocuments()
                    .stream()
                    .map(this::mapDocument)
                    .toList();
            dto.setDocuments(docs);
        }

        if (member.getInvestments() != null) {
            List<InvestmentDTO> investments = member.getInvestments()
                    .stream()
                    .map(this::mapInvestment)
                    .toList();
            dto.setInvestments(investments);
        }

        if (member.getInsurances() != null) {
            List<InsuranceDTO> insurances = member.getInsurances()
                    .stream()
                    .map(this::mapInsurance)
                    .toList();
            dto.setInsurances(insurances);
        }

        if (member.getMedicalRecords() != null) {
            List<MedicalRecordDTO> medicalRecords = member.getMedicalRecords()
                    .stream()
                    .map(this::mapMedicalRecord)
                    .toList();
            dto.setMedicalRecords(medicalRecords);
        }

        if (member.getProperties() != null) {
            List<PropertyDTO> properties = member.getProperties()
                    .stream()
                    .map(this::mapProperty)
                    .toList();
            dto.setProperties(properties);
        }

        if (member.getBankAccounts() != null) {
            List<BankAccountDTO> bankAccounts = member.getBankAccounts()
                    .stream()
                    .map(this::mapBankAccount)
                    .toList();
            dto.setBankAccounts(bankAccounts);
        }

        if (member.getContacts() != null) {
            List<ContactDTO> contacts = member.getContacts()
                    .stream()
                    .map(this::mapContact)
                    .toList();
            dto.setContacts(contacts);
        }

        if (member.getSubscriptions() != null) {
            List<SubscriptionDTO> subscriptions = member.getSubscriptions()
                    .stream()
                    .map(this::mapSubscription)
                    .toList();
            dto.setSubscriptions(subscriptions);
        }

        if (member.getLoans() != null) {
            List<LoanDTO> loans = member.getLoans()
                    .stream()
                    .map(this::mapLoan)
                    .toList();
            dto.setLoans(loans);
        }

        return dto;
    }

    private VehicleDTO mapVehicle(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setType(vehicle.getType());
        dto.setModel(vehicle.getModel());
        dto.setRegistrationNumber(vehicle.getRegistrationNumber());
        return dto;
    }

    private DocumentDTO mapDocument(Document doc) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(doc.getId());
        dto.setTitle(doc.getTitle());
        dto.setType(doc.getType());
        dto.setFilePath(doc.getFilePath());
        dto.setExpiryDate(doc.getExpiryDate());
        return dto;
    }

    private InvestmentDTO mapInvestment(Investment investment) {
        InvestmentDTO dto = new InvestmentDTO();
        dto.setId(investment.getId());
        dto.setType(investment.getType());
        dto.setName(investment.getName());
        dto.setInstitution(investment.getInstitution());
        dto.setInvestedAmount(investment.getInvestedAmount());
        dto.setCurrentValue(investment.getCurrentValue());
        dto.setStartDate(investment.getStartDate());
        dto.setMaturityDate(investment.getMaturityDate());
        return dto;
    }

    private InsuranceDTO mapInsurance(Insurance insurance) {
        InsuranceDTO dto = new InsuranceDTO();
        dto.setId(insurance.getId());
        dto.setType(insurance.getType());
        dto.setPolicyNumber(insurance.getPolicyNumber());
        dto.setProvider(insurance.getProvider());
        dto.setPremiumAmount(insurance.getPremiumAmount());
        dto.setSumAssured(insurance.getSumAssured());
        dto.setStartDate(insurance.getStartDate());
        dto.setExpiryDate(insurance.getExpiryDate());
        return dto;
    }

    private MedicalRecordDTO mapMedicalRecord(MedicalRecord record) {
        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setId(record.getId());
        dto.setType(record.getType());
        dto.setTitle(record.getTitle());
        dto.setDoctorName(record.getDoctorName());
        dto.setHospitalName(record.getHospitalName());
        dto.setRecordDate(record.getRecordDate());
        dto.setNotes(record.getNotes());
        dto.setFilePath(record.getFilePath());
        return dto;
    }

    private PropertyDTO mapProperty(Property property) {
        PropertyDTO dto = new PropertyDTO();
        dto.setId(property.getId());
        dto.setType(property.getType());
        dto.setAddress(property.getAddress());
        dto.setCity(property.getCity());
        dto.setState(property.getState());
        dto.setPurchaseDate(property.getPurchaseDate());
        dto.setPurchaseValue(property.getPurchaseValue());
        dto.setCurrentValue(property.getCurrentValue());
        dto.setIsRented(property.getIsRented());
        dto.setRentalIncome(property.getRentalIncome());
        return dto;
    }

    private BankAccountDTO mapBankAccount(BankAccount account) {
        BankAccountDTO dto = new BankAccountDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setBankName(account.getBankName());
        dto.setBranchName(account.getBranchName());
        dto.setIfscCode(account.getIfscCode());
        dto.setAccountType(account.getAccountType());
        dto.setBalance(account.getBalance());
        return dto;
    }

    private ContactDTO mapContact(Contact contact) {
        ContactDTO dto = new ContactDTO();
        dto.setId(contact.getId());
        dto.setName(contact.getName());
        dto.setCategory(contact.getCategory());
        dto.setPhone(contact.getPhone());
        dto.setEmail(contact.getEmail());
        dto.setOrganization(contact.getOrganization());
        dto.setNotes(contact.getNotes());
        return dto;
    }

    private SubscriptionDTO mapSubscription(Subscription subscription) {
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(subscription.getId());
        dto.setName(subscription.getName());
        dto.setCategory(subscription.getCategory());
        dto.setAmount(subscription.getAmount());
        dto.setBillingCycle(subscription.getBillingCycle());
        dto.setStartDate(subscription.getStartDate());
        dto.setRenewalDate(subscription.getRenewalDate());
        dto.setIsActive(subscription.getIsActive());
        dto.setNotes(subscription.getNotes());
        return dto;
    }

    private LoanDTO mapLoan(Loan loan) {
        LoanDTO dto = new LoanDTO();
        dto.setId(loan.getId());
        dto.setLoanType(loan.getLoanType());
        dto.setBankName(loan.getBankName());
        dto.setAccountNumber(loan.getAccountNumber());
        dto.setPrincipalAmount(loan.getPrincipalAmount());
        dto.setOutstandingAmount(loan.getOutstandingAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setEmiAmount(loan.getEmiAmount());
        dto.setStartDate(loan.getStartDate());
        dto.setEndDate(loan.getEndDate());
        dto.setStatus(loan.getStatus());
        return dto;
    }
}