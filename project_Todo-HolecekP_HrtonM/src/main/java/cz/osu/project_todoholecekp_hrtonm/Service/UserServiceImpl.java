package cz.osu.project_todoholecekp_hrtonm.Service;

import cz.osu.project_todoholecekp_hrtonm.Exception.RecordNotFoundException;
import cz.osu.project_todoholecekp_hrtonm.Exception.WrongInputFormatException;
import cz.osu.project_todoholecekp_hrtonm.Model.User;
import cz.osu.project_todoholecekp_hrtonm.Repository.UserRepository;

public class UserServiceImpl implements UserService{
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User newUser) {
        if(userRepository.existsById(newUser.getName())){
            throw new WrongInputFormatException("User already exists");
        }
        else if(newUser.getName().isEmpty()){
            throw new WrongInputFormatException("User cannot be empty");
        }else {
            return userRepository.save(newUser);
        }
    }

    @Override
    public User get(String name) {
        return userRepository.findById(name).orElseThrow(() -> new RecordNotFoundException("User does not exist"));
    }

    @Override
    public void delete(String name){
        boolean exists = userRepository.existsById(name);
        if (exists){
            userRepository.deleteById(name);
        }
        else throw new RecordNotFoundException("User does not exist");
    }
}
