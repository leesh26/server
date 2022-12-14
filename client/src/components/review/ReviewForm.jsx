import React, { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import styled from "styled-components";
import Button from "../common/Button";
import NewTagForm from "./ReviewTag";
import StarRating from "./ReviewStarRating";
import ReviewImageUpload from "./ReviewImageUpload";

const MainContainer = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  width: 1360px;
  height: 100%;
  margin-bottom: 150px;
  border-radius: 4px;
  border: 1px solid var(--gray-030);
  padding: 70px 20px;
`;

const FormContainer = styled.form`
  width: 90%;
`;

const TitleContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 600px;
  height: 65px;
  margin-bottom: 20px;
  span:first-child {
    width: 90px;
    font-size: 23px;
    font-weight: 700;
  }
  span:nth-child(2) {
    width: 490px;
    color: var(--green-010);
    font-size: 21px;
    font-weight: 700;
  }
`;

const NameContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 600px;
  height: 65px;
  span:first-child {
    width: 90px;
    font-size: 23px;
    font-weight: 700;
  }
  span:nth-child(2) {
    width: 490px;
    color: var(--green-010);
    font-size: 21px;
    font-weight: 700;
  }
`;

const ImgContainer = styled.div`
  display: flex;
  flex-direction: column;
  margin: 40px 0 0 10px;
  p {
    width: 600px;
    margin-top: 20px;
    color: var(--gray-020);
    font-size: 19px;
  }
`;

const ImgTitle = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  width: 600px;
  height: 10px;
  span {
    position: absolute;
    left: 0;
    font-size: 23px;
    font-weight: 700;
  }
`;

const TagContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 600px;
  height: 110px;
  margin: 30px 0 0 10px;
`;

const TagTitle = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  width: 600px;
  height: 40px;
  span {
    position: absolute;
    left: 0;
    font-size: 23px;
    font-weight: 700;
  }
`;

const TagBox = styled.div`
  width: 600px;
  height: 110px;
  margin-top: 20px;
`;

const StarContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 600px;
  height: 110px;
  margin: 30px 0 0 10px;
`;

const StarTitle = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  width: 600px;
  height: 20px;
  margin-top: 10px;
  span {
    position: absolute;
    left: 0;
    font-size: 23px;
    font-weight: 700;
  }
`;
const StarBox = styled.div`
  margin-top: 10px;
  width: 600px;
  height: 70px;
`;

const TextContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 800px;
  height: 190px;
  margin-top: 50px;

  textarea {
    border: none;
    background-color: #f0ece3;
    width: 800px;
    height: 100px;
    font-size: 20px;
    padding: 10px;
    margin-top: 10px;
  }
  textarea:focus {
    outline: none;
  }
`;

const TextTitle = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  width: 800px;
  height: 40px;
  span {
    position: absolute;
    left: 0;
    font-size: 23px;
    font-weight: 700;
  }
`;

const BtnContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 1100px;
  margin: 40px 0 0 560px;
`;

const ReviewForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [description, setDescription] = useState("");
  const [score, setScore] = useState("");
  const [tags, setTags] = useState();

  const onSubmitHandler = (e) => {
    e.preventDefault();
    let content = {
      review_img: 3,
      score: score,
      description: description,
      tags: tags,
    };

    console.log(content);

    axios
      .post(`http://175.125.6.189/cafe/${id}/reviews`, content)
      .then((res) => console.log(res.data))
      .then(() => {
        navigate(`/cafe/${id}`);
      })
      .catch((err) => {
        console.log("err", err);
      });
  };

  const onChangeStarHandler = (newStar) => {
    setScore(newStar);
    console.log(newStar);
  };

  const onChangeTagHandler = (newTags) => {
    setTags(newTags);
    console.log(tags);
  };

  return (
    <MainContainer>
      <FormContainer onSubmit={onSubmitHandler}>
        <TitleContainer>
          <span>?????????</span>
          <span>Mood</span>
        </TitleContainer>
        <NameContainer>
          <span>?????????</span>
          <span>?????????</span>
        </NameContainer>
        <ImgContainer>
          <ImgTitle>
            <span>?????????</span>
          </ImgTitle>
          <p>????????? ?????? ????????? ????????? ??????????????????. (?????? ??????)</p>
          <ReviewImageUpload />
        </ImgContainer>
        <TagContainer>
          <TagTitle>
            <span>??????</span>
          </TagTitle>
          <TagBox>
            <NewTagForm onChange={onChangeTagHandler} />
          </TagBox>
        </TagContainer>
        <StarContainer>
          <StarTitle>
            <span>??????</span>
          </StarTitle>
          <StarBox>
            <StarRating onChange={onChangeStarHandler} />
          </StarBox>
        </StarContainer>
        <TextContainer>
          <TextTitle>
            <span>?????????</span>
          </TextTitle>
          <textarea
            name="content"
            onChange={(e) => setDescription(e.target.value)}
          />
        </TextContainer>
        <BtnContainer>
          <Button>????????????</Button>
        </BtnContainer>
      </FormContainer>
    </MainContainer>
  );
};

export default ReviewForm;
