package seb39_40.coffeewithme.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import seb39_40.coffeewithme.cafe.domain.Cafe;
import seb39_40.coffeewithme.cafe.dto.CafeResponseDto;
import seb39_40.coffeewithme.cafe.mapper.CafeMapper;
import seb39_40.coffeewithme.image.domain.Image;
import seb39_40.coffeewithme.image.repository.ImageRepository;
import seb39_40.coffeewithme.image.service.ImageService;
import seb39_40.coffeewithme.review.domain.Review;
import seb39_40.coffeewithme.review.repository.ReviewRepository;
import seb39_40.coffeewithme.user.domain.User;
import seb39_40.coffeewithme.user.dto.UserRequestDto;
import seb39_40.coffeewithme.user.dto.UserResponseDto;
import seb39_40.coffeewithme.user.dto.UserReviewResponseDto;
import seb39_40.coffeewithme.user.dto.WishlistResponse;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "name", source = "userName")
    UserResponseDto.SimpleUserInfo userToUserSimpleInfoDto(User user);
    User userJoinToUser(UserRequestDto.UserJoin userJoin);
    UserResponseDto.UserInfo userToUserInfo(User user);

    default WishlistResponse cafesToWishlistDto(List<CafeResponseDto.SimpleCafeInfo> cafes){
        return new WishlistResponse(cafes);
    }

    default UserReviewResponseDto reviewsToUserReviewResponseDto(List<Review> reviews){
        if(reviews==null)
            return new UserReviewResponseDto();
        List<UserReviewResponseDto.ReviewSimpleDto> rsdl=reviews.stream().map(r -> {
            UserReviewResponseDto.ReviewSimpleDto rsd=new UserReviewResponseDto.ReviewSimpleDto();
            rsd.setId(r.getId());
            rsd.setDescription(r.getDescription());
            rsd.setReviewImg(r.getReviewImg().getPath());
            rsd.setReviewTags(r.getReviewTags());
            rsd.setScore(r.getScore());
            rsd.setCafe(new UserReviewResponseDto.ReviewSimpleCafeDto(r.getCafe().getId(),r.getCafe().getName()));
            return rsd;
        }).collect(Collectors.toList());
        return new UserReviewResponseDto(rsdl);
    }

}

